package com.app.data.notifier.service;

public interface NotifierService {
    void subscribeNotification(String jwt, String city, String weather);

    void unsubscribeNotification(String jwt, String city, String weather);

    void unsubscribeAllNotifications(String jwt);

    String getEmailFromJwt(String jwt);
}
