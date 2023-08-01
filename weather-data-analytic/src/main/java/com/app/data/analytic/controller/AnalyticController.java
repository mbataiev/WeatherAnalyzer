package com.app.data.analytic.controller;

import com.app.data.analytic.service.DataAnalyticService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/analytic")
@AllArgsConstructor
@Slf4j
@Tag(
        name = "Weather Analytic",
        description = "Analyse storage data"
)
public class AnalyticController {

    private DataAnalyticService service;


    @GetMapping("/avgtemp")
    public ResponseEntity<Double> getAvgTempByPeriodAndCity(@RequestParam String city,
                                                            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        Double averageTemperature = service.getAvgTempByPeriodAndCity(startDate, endDate, city);
        return ResponseEntity.ok(averageTemperature);
    }

    @GetMapping("/weather")
    public ResponseEntity<String> getCommonWeather(@RequestParam String city,
                                                   @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                   @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        String commonWeather = service.getPopularWeatherByPeriodAndCity(startDate, endDate, city);
        return ResponseEntity.ok(commonWeather);
    }


}
