package com.app.data.notifier.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserNotificationDto {
    private Long id;
    @NotBlank
    private String email;
    @NotBlank
    private String city;
    @NotBlank
    private ValidWeather weather;
}
