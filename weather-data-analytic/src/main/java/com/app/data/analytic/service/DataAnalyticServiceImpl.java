package com.app.data.analytic.service;

import com.app.common.domains.EventStatus;
import com.app.data.analytic.entity.WeatherData;
import com.app.data.analytic.exception.ResourceNotFoundException;
import com.app.data.analytic.mapper.WeatherMapper;
import com.app.data.analytic.repository.AnalyticRepository;
import com.app.common.domains.WeatherDataDto;
import com.app.common.domains.WeatherEvent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class DataAnalyticServiceImpl implements DataAnalyticService {

    private AnalyticRepository repository;
    private WeatherMapper mapper;

    @Override
    public void processEvent(WeatherEvent event) {
        if (event.getStatus() == EventStatus.SAVED) {
            saveWeather(event.getWeatherData());
        }
    }

    @Override
    public WeatherDataDto saveWeather(WeatherDataDto data) {
        WeatherData weather = mapper.dtoToWeather(data);
        return mapper.weatherToDto(repository.save(weather));
    }

    @Override
    public Double getAvgTempByPeriodAndCity(@NotNull LocalDate startDate,
                                            @NotNull LocalDate endDate,
                                            @NotBlank String city) {
        if (repository.findByCity(city).isEmpty()) {
            throw new ResourceNotFoundException("City", "city", city);
        }
        return repository.findAvgTemperatureByCityAndPeriod(city, startDate, endDate);
    }

    @Override
    public String getPopularWeatherByPeriodAndCity(@NotNull LocalDate startDate,
                                                   @NotNull LocalDate endDate,
                                                   @NotBlank String city) {
        if (repository.findByCity(city).isEmpty()) {
            throw new ResourceNotFoundException("City", "city", city);
        }
        return repository.findPopularWeatherByCityAndPeriod(city, startDate, endDate);
    }

}
