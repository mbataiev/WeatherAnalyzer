package com.app.weather.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeatherDataDto {
    private Long id;
    @NotEmpty(message = "City can not be empty!")
    private String city;
    @NotEmpty(message = "Weather can not be empty!")
    private String weather;
    @NotEmpty(message = "Temperature can not be empty!")
    private Double temperature;
    @NotEmpty(message = "Wind speed can not be empty!")
    private Double windSpeed;
    @NotEmpty(message = "Visibility can not be empty!")
    private Double visibility;
    @NotEmpty(message = "Created date can not be empty!")
    private LocalDateTime createdDate;
}
