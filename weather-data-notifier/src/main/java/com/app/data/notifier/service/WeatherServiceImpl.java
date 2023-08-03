package com.app.data.notifier.service;

import com.app.data.notifier.repository.UserNotificationRepository;
import com.app.domains.EventStatus;
import com.app.domains.WeatherDataDto;
import com.app.domains.WeatherEvent;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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
        List<String> emails = repository.findAllEmailByWeather(weather);
        sendNotification(emails, weather);
    }

    @Override
    public void sendNotification(List<String> emails, String weather) {
        // TODO: 03.08.2023 ADD JAVA MAIL API TO SEND NOTIFICATION
    }
}
