package com.app.domains;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WeatherEvent {
    private String message;
    private EventStatus status;
    private WeatherDataDto weatherData;
    private Long version;
}
