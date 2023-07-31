package com.app.data.processor.service;

import com.app.common.domains.EventStatus;
import com.app.common.domains.WeatherDataDto;
import com.app.common.domains.WeatherEvent;
import com.app.data.processor.kafka.EventProducer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class DataProcessorServiceImpl implements DataProcessorService {

    private EventProducer producer;

    @Override
    public WeatherDataDto parseJson(String json) {
        return JsonParser.jsonToWeatherDto(json);
    }

    @Override
    public void publishEvent(WeatherDataDto processedData) {
        WeatherEvent event = WeatherEvent.builder()
                .message("Successfully processed")
                .status(EventStatus.PROCESSED)
                .weatherData(processedData)
                .version(1L)
                .build();
        log.info("publishEvent : Published event -> {}", event);
        producer.sendMessage(event);
    }
}
