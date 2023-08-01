package com.app.data.analytic.service;

import com.app.domains.WeatherDataDto;
import com.app.domains.WeatherEvent;

import java.time.LocalDate;

public interface DataAnalyticService {
    void processEvent(WeatherEvent event);
    WeatherDataDto saveWeather(WeatherDataDto data);

    Double getAvgTempByPeriodAndCity(LocalDate startDate, LocalDate endDate, String city);
    String getPopularWeatherByPeriodAndCity(LocalDate startDate, LocalDate endDate, String city);
}
