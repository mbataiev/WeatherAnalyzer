package com.app.data.analytic.service;

import com.app.common.domains.WeatherDataDto;
import com.app.common.domains.WeatherEvent;

import java.time.LocalDate;

public interface DataAnalyticService {
    void processEvent(WeatherEvent event);
    WeatherDataDto saveWeather(WeatherDataDto data);
    Double getAvgTempByPeriodAndCity(LocalDate startDate, LocalDate endDate, String city);
    String getPopularWeatherByPeriodAndCity(LocalDate startDate, LocalDate endDate, String city);
}
