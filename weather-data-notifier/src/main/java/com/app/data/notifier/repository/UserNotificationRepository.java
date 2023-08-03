package com.app.data.notifier.repository;

import com.app.data.notifier.dto.ValidWeather;
import com.app.data.notifier.entity.UserNotification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserNotificationRepository extends JpaRepository<UserNotification,Long> {
    Optional<UserNotification> findByEmailAndCityAndWeather(String email, String city, ValidWeather weather);

    void deleteAllByEmail(String email);

    List<UserNotification> findAllByEmail(String email);

}
