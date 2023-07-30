package com.app.data.processor.service;

import com.app.data.processor.domains.WeatherDataDto;
import org.springframework.stereotype.Service;

@Service
public class DataProcessorServiceImpl implements DataProcessorService {

    @Override
    public WeatherDataDto parseJson(String json) {
        return JsonParser.jsonToWeatherDto(json);
    }
}
