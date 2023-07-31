package com.app.data.storage.service;

import com.app.common.domains.WeatherDataDto;
import com.app.common.domains.WeatherEvent;

public interface DataStorageService {
    void processEvent(WeatherEvent event);
    WeatherDataDto saveWeather(WeatherDataDto data);

    void publishEvent(WeatherEvent event);

    WeatherDataDto getWeatherByDateAndCity(String city, String date);
}
