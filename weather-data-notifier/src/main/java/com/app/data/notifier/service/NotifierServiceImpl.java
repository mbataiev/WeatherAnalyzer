package com.app.data.notifier.service;

import com.app.data.notifier.config.JwtUtils;
import com.app.data.notifier.dto.ValidWeather;
import com.app.data.notifier.entity.UserNotification;
import com.app.data.notifier.exception.NotifyException;
import com.app.data.notifier.repository.UserNotificationRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@Transactional
@Slf4j
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
            throw new NotifyException("Notify already exist");
        }
        UserNotification userNotification = new UserNotification();
        userNotification.setEmail(email);
        userNotification.setCity(city);
        userNotification.setWeather(ValidWeather.fromString(weather));
        log.debug("User notification -> {}", userNotification);
        repository.save(userNotification);
    }

    @Override
    public void unsubscribeNotification(String jwt, String city, String weather) {
        validateWeather(weather);
        String email = getEmailFromJwt(jwt);

        UserNotification notification = repository.findByEmailAndCityAndWeather(email, city, ValidWeather.fromString(weather))
                .orElseThrow(
                        () -> new NotifyException("Notify does not exist")
                );
        log.debug("User notification -> {}", notification);
        repository.delete(notification);
    }

    @Override
    public void unsubscribeAllNotifications(String jwt) {
        String email = getEmailFromJwt(jwt);
        repository.deleteAllByEmail(email);
    }

    @Override
    public String getEmailFromJwt(String jwt) {
        return jwtUtils.getEmailFromJwt(jwt);
    }

    @Override
    public List<UserNotification> getAllNotificationsByEmail(String jwt) {
        String email = jwtUtils.getEmailFromJwt(jwt);
        return repository.findAllByEmail(email);
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
