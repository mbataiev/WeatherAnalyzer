package com.app.data.storage.service;

import com.app.common.domains.EventStatus;
import com.app.common.domains.WeatherDataDto;
import com.app.common.domains.WeatherEvent;
import com.app.data.storage.entity.WeatherData;
import com.app.data.storage.exception.ProcessingException;
import com.app.data.storage.kafka.EventProducer;
import com.app.data.storage.mapper.WeatherMapper;
import com.app.data.storage.repository.WeatherRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DataStorageServiceImpl implements DataStorageService {

    private WeatherRepository repository;
    private WeatherMapper mapper;
    private EventProducer producer;

    @Override
    public void processEvent(WeatherEvent event) {
        if (event.getStatus() == EventStatus.ERROR) {
            throw new ProcessingException(String.format("Processing exception with event -> %s", event));
        }
        if (event.getStatus() == EventStatus.PROCESSED) {
            WeatherDataDto savedData = saveWeather(event.getWeatherData());
            WeatherEvent savedEvent = WeatherEvent.builder()
                    .weatherData(savedData)
                    .status(EventStatus.SAVED)
                    .version(event.getVersion() + 1)
                    .message("Successfully saved to db")
                    .build();

            publishEvent(savedEvent);
        }
    }

    @Override
    public WeatherDataDto saveWeather(WeatherDataDto data) {
        WeatherData weather = mapper.dtoToWeather(data);
        return mapper.weatherToDto(repository.save(weather));
    }

    @Override
    public void publishEvent(WeatherEvent event) {
        producer.sendMessage(event);
    }
}
