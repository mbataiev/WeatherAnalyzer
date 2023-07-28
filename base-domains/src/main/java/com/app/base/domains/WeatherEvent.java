package com.app.base.domains;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeatherEvent {
    private String message;
    private EventStatus status;
    private WeatherDataDto weatherData;
    private Long version;
}
