package com.app.weather.service;


import com.app.weather.domains.WeatherDataDto;

public interface WeatherService {
    WeatherDataDto getWeatherDataFromApi(String cityName);
}
