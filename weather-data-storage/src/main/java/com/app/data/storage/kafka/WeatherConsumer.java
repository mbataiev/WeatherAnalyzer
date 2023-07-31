package com.app.data.storage.kafka;

import com.app.common.domains.WeatherEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class WeatherConsumer {

    @KafkaListener(
            topics = "${spring.kafka.topic.name}",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consume(WeatherEvent event) {
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        System.out.println(event);
        log.info(String.format("Order event received in email service => %s", event));
    }

}
