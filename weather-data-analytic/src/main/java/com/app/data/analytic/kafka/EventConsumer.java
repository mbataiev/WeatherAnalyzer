package com.app.data.analytic.kafka;

import com.app.data.analytic.service.DataAnalyticService;
import com.app.common.domains.WeatherEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class EventConsumer {

    private DataAnalyticService service;

    @KafkaListener(
            topics = "${spring.kafka.topic.name}",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consume(WeatherEvent event) {
        log.info("Weather event received in analyse service => {}", event);
        service.processEvent(event);
    }

}
