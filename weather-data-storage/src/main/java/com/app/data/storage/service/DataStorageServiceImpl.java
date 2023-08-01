package com.app.data.storage.service;

import com.app.common.domains.EventStatus;
import com.app.common.domains.WeatherDataDto;
import com.app.common.domains.WeatherEvent;
import com.app.data.storage.entity.WeatherData;
import com.app.data.storage.kafka.EventProducer;
import com.app.data.storage.mapper.WeatherMapper;
import com.app.data.storage.repository.WeatherRepository;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class DataStorageServiceImpl implements DataStorageService {

    private WeatherRepository repository;
    private WeatherMapper mapper;
    private EventProducer producer;

    @Override
    public void processEvent(WeatherEvent event) {
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

    @Override
    public WeatherDataDto getWeatherByDateAndCity(@NotNull String city,
                                                  @NotNull @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String date) {
        LocalDate searchDate = LocalDate.parse(date);
        List<WeatherData> list = repository.findByCityAndDate(city, searchDate);
        log.debug(list.toString());
        return list.isEmpty() ? null : mapper.weatherToDto(list.get(0));
    }
}
