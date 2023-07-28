package com.app.weather.mapper;

import com.app.base.domains.WeatherDataDto;
import com.app.weather.entity.WeatherData;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WeatherMapper {
    WeatherData dtoToWeather(WeatherDataDto dto);
    WeatherDataDto weatherToDto(WeatherData weatherData);
}
