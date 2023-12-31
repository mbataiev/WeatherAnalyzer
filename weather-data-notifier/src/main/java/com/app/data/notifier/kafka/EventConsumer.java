package com.app.data.notifier.kafka;

import com.app.data.notifier.service.WeatherService;
import com.app.common.domains.WeatherEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class EventConsumer {

    private WeatherService service;

    @KafkaListener(
            topics = "${spring.kafka.topic.name}",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consume(WeatherEvent event) {
        log.debug("Weather event -> {}", event);
        service.processEvent(event);
    }

}
