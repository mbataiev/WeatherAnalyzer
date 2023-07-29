package com.app.weather.controller;

import com.app.weather.service.WeatherService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/weather")
@AllArgsConstructor
@Tag(
        name = "Produce weather data",
        description = "Collect and produce weather data from http://api.openweathermap.org"
)
public class WeatherController {

    private final WeatherService weatherService;

    @GetMapping()
    public String getWeatherData(@RequestParam String cityName) {
        return weatherService.getWeatherDataFromApi(cityName);
//        if (weatherData != null) {
//            producer.sendMessage(weatherData);
//            return ResponseEntity.ok(weatherData);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
    }

}
