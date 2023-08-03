package com.app.data.notifier.service;

import com.app.domains.WeatherDataDto;
import com.app.domains.WeatherEvent;

import java.util.List;

public interface WeatherService {

    void processEvent(WeatherEvent event);

    void filterNotification(WeatherDataDto weatherData);

    void sendNotification(List<String> emails, String weather);
}
