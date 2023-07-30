package com.app.data.processor.service;

import com.app.data.processor.domains.WeatherDataDto;

public interface DataProcessorService {
    WeatherDataDto parseJson(String json);

    void publishEvent(WeatherDataDto processedData);
}
