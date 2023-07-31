package com.app.weather.service;


import com.app.common.domains.WeatherDataDto;

public interface WeatherService {
    WeatherDataDto getWeatherDataFromApi(String cityName);
}
