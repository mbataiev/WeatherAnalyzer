package com.app.data.analytic.mapper;


import com.app.data.analytic.entity.WeatherData;
import com.app.domains.WeatherDataDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WeatherMapper {
    WeatherDataDto weatherToDto(WeatherData data);
    WeatherData dtoToWeather(WeatherDataDto dataDto);

}
