package com.app.weather.mapper;

import com.app.weather.dto.WeatherDataDto;
import com.google.gson.JsonObject;

public class JsonParser {
    public WeatherDataDto jsonToWeatherDto(String json) {
        com.google.gson.JsonParser jsonParser = new com.google.gson.JsonParser();

        JsonObject jsonObject = jsonParser.parse(json).getAsJsonObject();

        String weatherName = jsonObject.getAsJsonArray("weather")
                .get(0).getAsJsonObject().get("main").getAsString();
        Double temperature = jsonObject.getAsJsonObject("main").get("temp").getAsDouble();
        Double windSpeed = jsonObject.getAsJsonObject("wind").get("speed").getAsDouble();
        Double visibility = jsonObject.get("visibility").getAsDouble();

        WeatherDataDto weatherData = new WeatherDataDto();
        weatherData.setWeather(weatherName);
        weatherData.setTemperature(temperature);
        weatherData.setWindSpeed(windSpeed);
        weatherData.setVisibility(visibility);
        return weatherData;
    }
}
