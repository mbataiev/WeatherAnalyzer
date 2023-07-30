package com.app.data.processor.controller;

import com.app.data.processor.domains.WeatherDataDto;
import com.app.data.processor.service.DataProcessorService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/process")
@AllArgsConstructor
public class DataProcessorController {

    private DataProcessorService service;

    @PostMapping
    public WeatherDataDto parseWeatherData(@RequestBody String json,
                                           @RequestParam("city") String city) {
        WeatherDataDto processedData = service.parseJson(json);
        processedData.setCity(city);
        return processedData;
    }

}
