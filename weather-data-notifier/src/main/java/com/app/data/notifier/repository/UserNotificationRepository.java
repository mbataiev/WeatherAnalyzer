package com.app.data.notifier.repository;

import com.app.data.notifier.entity.UserNotification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserNotificationRepository extends JpaRepository<UserNotification,Long> {
}
