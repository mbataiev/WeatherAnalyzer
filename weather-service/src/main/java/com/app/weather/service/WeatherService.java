package com.app.weather.service;


import com.app.common.domains.WeatherDataDto;

import java.util.Optional;

public interface WeatherService {
    WeatherDataDto getWeatherDataFromApi(String cityName);

    Optional<WeatherDataDto> postWeather(String json, String city);
}
