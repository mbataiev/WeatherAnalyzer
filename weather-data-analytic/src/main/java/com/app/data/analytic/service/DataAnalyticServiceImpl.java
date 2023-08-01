package com.app.data.analytic.service;

import com.app.data.analytic.repository.AnalyticRepository;
import com.app.common.domains.WeatherDataDto;
import com.app.common.domains.WeatherEvent;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class DataAnalyticServiceImpl implements DataAnalyticService {

    private AnalyticRepository repository;

    @Override
    public void processEvent(WeatherEvent event) {

    }

    @Override
    public WeatherDataDto saveWeather(WeatherDataDto data) {
        return null;
    }

    @Override
    public Double getAvgTempByPeriodAndCity(LocalDate startDate, LocalDate endDate, String city) {
        return null;
    }

    @Override
    public String getPopularWeatherByPeriodAndCity(LocalDate startDate, LocalDate endDate, String city) {
        return null;
    }
}
