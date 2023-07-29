package com.app.data.processor.service;


import com.app.data.processor.domains.WeatherDataDto;
import com.app.data.processor.exception.ResourceNotFoundException;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;

@Slf4j
@Service
public class JsonParser {

    private static final Double FROM_KELVIN_TO_CELSIUS = 273.15;
    private static final Double FROM_MPH_TO_MS = 0.44704;

    public static WeatherDataDto jsonToWeatherDto(String json) {
        if (json == null) throw new ResourceNotFoundException("Json", "json", null);

        com.google.gson.JsonParser jsonParser = new com.google.gson.JsonParser();
        JsonObject jsonObject;
        try {
            jsonObject = jsonParser.parse(json).getAsJsonObject();
        } catch (Exception e) {
            log.error(String.format("Invalid json -> %s", json));
            throw new ResourceNotFoundException("Json", "json", json);
        }
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
