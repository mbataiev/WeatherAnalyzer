package com.app.weather.repository;

import com.app.weather.entity.WeatherData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherRepository extends JpaRepository<WeatherData, Long> {
}
