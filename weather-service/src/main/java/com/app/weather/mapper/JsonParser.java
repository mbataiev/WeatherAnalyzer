package com.app.weather.mapper;

import com.app.weather.dto.WeatherDataDto;
import com.google.gson.JsonObject;

import java.text.DecimalFormat;

public class JsonParser {

    private static final Double FROM_KELVIN_TO_CELSIUS = 273.15;
    private static final Double FROM_MPH_TO_MS = 0.44704;

    public static WeatherDataDto jsonToWeatherDto(String json) {
        com.google.gson.JsonParser jsonParser = new com.google.gson.JsonParser();

        JsonObject jsonObject = jsonParser.parse(json).getAsJsonObject();

        String weatherName = jsonObject.getAsJsonArray("weather")
                .get(0).getAsJsonObject().get("main").getAsString();
        Double temperature = jsonObject.getAsJsonObject("main").get("temp").getAsDouble();
        Double windSpeed = jsonObject.getAsJsonObject("wind").get("speed").getAsDouble();
        Double visibility = jsonObject.get("visibility").getAsDouble();

        //convert from Kelvin to Celsius
        temperature -= FROM_KELVIN_TO_CELSIUS;
        //convert from mph to m/s
        windSpeed *= FROM_MPH_TO_MS;

        // Format values to two decimal places
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        temperature = Double.parseDouble(decimalFormat.format(temperature));
        windSpeed = Double.parseDouble(decimalFormat.format(windSpeed));

        WeatherDataDto weatherData = new WeatherDataDto();
        weatherData.setWeather(weatherName);
        weatherData.setTemperature(temperature);
        weatherData.setWindSpeed(windSpeed);
        weatherData.setVisibility(visibility);
        return weatherData;
    }
}
