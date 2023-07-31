package com.app.data.storage.repository;

import com.app.data.storage.entity.WeatherData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherRepository extends JpaRepository<WeatherData, Long> {
}