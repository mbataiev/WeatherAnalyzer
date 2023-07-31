package com.app.data.storage.mapper;

import com.app.common.domains.WeatherDataDto;
import com.app.data.storage.entity.WeatherData;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WeatherMapper {
    WeatherDataDto weatherToDto(WeatherData data);
    WeatherData dtoToWeather(WeatherDataDto dataDto);

}
