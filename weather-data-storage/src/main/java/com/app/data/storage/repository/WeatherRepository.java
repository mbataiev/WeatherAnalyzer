package com.app.data.storage.repository;

import com.app.data.storage.entity.WeatherData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface WeatherRepository extends JpaRepository<WeatherData, Long> {
    @Query("SELECT w FROM WeatherData w WHERE w.city = :city AND DATE(w.createdDate) = :date")
    List<WeatherData> findByCityAndDate(@Param("city") String city, @Param("date") LocalDate date);
}