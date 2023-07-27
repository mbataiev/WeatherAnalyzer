package com.app.weather.controller;

import com.app.weather.dto.WeatherDataDto;
import com.app.weather.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    private final WeatherService weatherService;

    @Autowired
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping()
    public ResponseEntity<WeatherDataDto> getWeatherData(@RequestParam String cityName) {
        WeatherDataDto weatherData = weatherService.getWeatherDataFromApi(cityName);
        if (weatherData != null) {
            return ResponseEntity.ok(weatherData);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
