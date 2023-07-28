package com.app.weather.kafka;

import com.app.weather.dto.WeatherDataDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class WeatherProducer {
    private NewTopic topic;
    private KafkaTemplate<String, WeatherDataDto> template;

    public void sendMessage(WeatherDataDto weatherData) {
        log.info("WeatherProducer.sendMessage() : Weather data -> {}", weatherData);

        Message<WeatherDataDto> message = MessageBuilder
                .withPayload(weatherData)
                .setHeader(KafkaHeaders.TOPIC, topic.name())
                .build();

        template.send(message);
    }

}
