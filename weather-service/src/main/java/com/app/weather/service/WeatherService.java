package com.app.weather.service;

import com.app.weather.dto.WeatherDataDto;

public interface WeatherService {
    WeatherDataDto getWeatherDataFromApi(String cityName);
}
