package com.app.data.notifier.service;

import com.app.data.notifier.config.JwtUtils;
import com.app.data.notifier.dto.ValidWeather;
import com.app.data.notifier.entity.UserNotification;
import com.app.data.notifier.repository.UserNotificationRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
@AllArgsConstructor
public class NotifierServiceImpl implements NotifierService {

    private JwtUtils jwtUtils;
    private UserNotificationRepository repository;

    @Override
    public void subscribeNotification(String jwt, String city, String weather) {
        validateWeather(weather);
        // TODO: 03.08.2023 validate city
        String email = getEmailFromJwt(jwt);
        if (repository.findByEmailAndCityAndWeather(email, city, ValidWeather.fromString(weather)).isPresent()) {
            throw new RuntimeException("Notify already exist");
        }
        UserNotification userNotification = new UserNotification();
        userNotification.setEmail(email);
        userNotification.setCity(city);
        userNotification.setWeather(ValidWeather.fromString(weather));
        repository.save(userNotification);
    }

    @Override
    public void unsubscribeNotification(String jwt, String city, String weather) {
        validateWeather(weather);
// TODO: 03.08.2023 add logger and exception handler
        String email = getEmailFromJwt(jwt);

        UserNotification notification = repository.findByEmailAndCityAndWeather(email, city, ValidWeather.fromString(weather))
                .orElseThrow(
                        () -> new NoSuchElementException("No such notify")
                );

        repository.delete(notification);
    }

    @Override
    public void unsubscribeAllNotifications(String jwt) {
        String email = getEmailFromJwt(jwt);
// TODO: 03.08.2023 add logger and exception handler
        repository.deleteAllByEmail(email);
    }

    @Override
    public String getEmailFromJwt(String jwt) {
        return jwtUtils.getEmailFromJwt(jwt);
    }

    @Override
    public List<UserNotification> getAllNotificationsByEmail(String jwt) {
// TODO: 03.08.2023 add logger and exception handler
        String email = jwtUtils.getEmailFromJwt(jwt);
        return repository.findAllByEmail(email);
    }

    public void validateWeather(String weather) {
// TODO: 03.08.2023 add logger and exception handler
        if (!isValidWeather(weather)) {
            throw new IllegalArgumentException("Invalid weather: " + weather);
        }
    }

    public boolean isValidWeather(String weather) {
// TODO: 03.08.2023 add logger and exception handler
        return Arrays.stream(ValidWeather.values()).anyMatch(w -> w.name().equalsIgnoreCase(weather));
    }
}
