package com.app.data.notifier.service;

import com.app.data.notifier.dto.ValidWeather;
import com.app.data.notifier.repository.UserNotificationRepository;
import com.app.common.domains.EventStatus;
import com.app.common.domains.WeatherDataDto;
import com.app.common.domains.WeatherEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class WeatherServiceImpl implements WeatherService {

    private UserNotificationRepository repository;

    @Override
    public void processEvent(WeatherEvent event) {
        if (event.getStatus() == EventStatus.SAVED) {
            filterNotification(event.getWeatherData());
        }
    }

    @Override
    public void filterNotification(WeatherDataDto weatherData) {
        String weather = weatherData.getWeather();
        List<String> emails = repository.findAllEmailByWeather(ValidWeather.fromString(weather));
        log.debug("Sending notification to emails -> {}", emails);
        sendNotification(emails, weather);
    }

    @Override
    public void sendNotification(List<String> emails, String weather) {
        // TODO: 03.08.2023 ADD JAVA MAIL API TO SEND NOTIFICATION
    }
}
