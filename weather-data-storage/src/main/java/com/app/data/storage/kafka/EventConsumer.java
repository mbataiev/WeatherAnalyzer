package com.app.data.storage.kafka;

import com.app.common.domains.WeatherEvent;
import com.app.data.storage.service.DataStorageService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class EventConsumer {

    private DataStorageService service;

    @KafkaListener(
            topics = "${spring.kafka.topic.name}",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consume(WeatherEvent event) {
        log.info(String.format("Order event received in email service => %s", event));
        service.processEvent(event);
    }

}
