package com.app.data.notifier.dto;

import lombok.Getter;

@Getter
public enum ValidWeather {
    Clouds,
    Rain,
    Clear,
    Thunderstorm;

    public static ValidWeather fromString(String value) {
        String formattedValue = value.substring(0, 1).toUpperCase() + value.substring(1).toLowerCase();
        return ValidWeather.valueOf(formattedValue);
    }
    }
