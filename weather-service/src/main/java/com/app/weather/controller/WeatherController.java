package com.app.weather.controller;

import com.app.common.domains.WeatherDataDto;
import com.app.weather.service.WeatherService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/weather")
@AllArgsConstructor
@Slf4j
@Tag(
        name = "Produce weather data",
        description = "Collect and produce weather data from http://api.openweathermap.org"
)
public class WeatherController {

    private final WeatherService weatherService;

    @GetMapping()
    public ResponseEntity<WeatherDataDto> getWeatherData(@RequestParam String cityName) {
        WeatherDataDto response = weatherService.getWeatherDataFromApi(cityName);
        log.info("getWeatherData : collected data -> {}", response);
        return ResponseEntity.ok(response);
    }

}
