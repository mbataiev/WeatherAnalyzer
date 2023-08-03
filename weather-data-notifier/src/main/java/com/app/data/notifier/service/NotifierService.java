package com.app.data.notifier.service;

import com.app.data.notifier.entity.UserNotification;

import java.util.List;

public interface NotifierService {
    void subscribeNotification(String jwt, String city, String weather);

    void unsubscribeNotification(String jwt, String city, String weather);

    void unsubscribeAllNotifications(String jwt);

    String getEmailFromJwt(String jwt);

    List<UserNotification> getAllNotificationsByEmail(String jwt);
}
