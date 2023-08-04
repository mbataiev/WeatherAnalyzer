package com.app.data.storage.controller;

import com.app.common.domains.WeatherDataDto;
import com.app.data.storage.service.DataStorageService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/storage")
@AllArgsConstructor
@Tag(
        name = "Storage weather data",
        description = "Process storage data"
)
public class DataStorageController {

    public DataStorageService service;

    @GetMapping("/{city}/{date}")
    public ResponseEntity<WeatherDataDto> getWeatherByDateAndCity(@PathVariable String city, @PathVariable String date) {
        WeatherDataDto weatherData = service.getWeatherByDateAndCity(city, date);
        return ResponseEntity.ok(weatherData);
    }

}
