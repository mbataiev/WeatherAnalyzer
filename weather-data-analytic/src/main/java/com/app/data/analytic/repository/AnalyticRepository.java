package com.app.data.analytic.repository;

import com.app.data.analytic.entity.WeatherData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface AnalyticRepository extends JpaRepository<WeatherData, Long> {


    @Query("SELECT AVG(w.temperature) FROM WeatherData w " +
            "WHERE w.city = :city AND DATE(w.createdDate) BETWEEN :startDate AND :endDate")
    Double findAvgTemperatureByCityAndPeriod(String city, LocalDate startDate, LocalDate endDate);

    @Query("SELECT w.weather FROM WeatherData w " +
            "WHERE w.city = :city AND DATE(w.createdDate) BETWEEN :startDate AND :endDate " +
            "GROUP BY w.weather ORDER BY COUNT(w.weather) DESC")
    String findPopularWeatherByCityAndPeriod(@Param("city") String city, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    List<WeatherData> findByCity(String city);
}