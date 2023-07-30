package com.app.data.processor.controller;

import com.app.data.processor.domains.WeatherDataDto;
import com.app.data.processor.service.DataProcessorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/process")
@AllArgsConstructor
@Slf4j
@Tag(
        name = "Process weather data",
        description = "Receive weather json from Weather Service, processes it and passes on"
)
public class DataProcessorController {

    private DataProcessorService service;

    @PostMapping
    public WeatherDataDto parseWeatherData(@RequestBody String json,
                                           @RequestParam("city") String city) {
        WeatherDataDto processedData = service.parseJson(json);
        processedData.setCity(city);
        log.info("parseWeatherData : Publish data -> {}",processedData);
        service.publishEvent(processedData);
        return processedData;
    }

}
