package com.app.data.notifier.service;

import com.app.data.notifier.config.JwtUtils;
import com.app.data.notifier.dto.ValidWeather;
import com.app.data.notifier.entity.UserNotification;
import com.app.data.notifier.repository.UserNotificationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@AllArgsConstructor
public class NotifierServiceImpl implements NotifierService {

    private JwtUtils jwtUtils;
    private UserNotificationRepository repository;


    @Override
    public void subscribeNotification(String jwt, String city, String weather) {
        validateWeather(weather);
        // TODO: 03.08.2023 validate city

        String email = getEmailFromJwt(jwt);

        UserNotification userNotification = new UserNotification();
        userNotification.setEmail(email);
        userNotification.setCity(city);
        userNotification.setWeather(ValidWeather.fromString(weather));
        repository.save(userNotification);
    }

    @Override
    public void unsubscribeNotification(String jwt, String city, String weather) {

    }

    @Override
    public void unsubscribeAllNotifications(String jwt) {

    }

    @Override
    public String getEmailFromJwt(String jwt) {
        return jwtUtils.getEmailFromJwt(jwt);
    }

    public void validateWeather(String weather) {
        if (!isValidWeather(weather)) {
            throw new IllegalArgumentException("Invalid weather: " + weather);
        }
    }

    public boolean isValidWeather(String weather) {
        return Arrays.stream(ValidWeather.values()).anyMatch(w -> w.name().equalsIgnoreCase(weather));
    }
}
