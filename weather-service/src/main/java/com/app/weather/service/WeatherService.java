package com.app.weather.service;

import com.app.base.domains.WeatherDataDto;

public interface WeatherService {
    WeatherDataDto getWeatherDataFromApi(String cityName);
}
