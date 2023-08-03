package com.app.data.notifier.controller;

import com.app.data.notifier.service.NotifierService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/notify")
@AllArgsConstructor
@Tag(
        name = "Notifier Controller",
        description = "Sub and unsub to weather info"
)
public class NotifierController {

    private NotifierService service;

    @PostMapping("/subscribe")
    public ResponseEntity<String> subscribeNotification(@RequestHeader("Authorization") @NonNull String authorizationHeader,
                                                        @RequestParam("cityName") @NonNull String city,
                                                        @RequestParam("weather") @NonNull String weather) {
        String jwt = authorizationHeader.substring(7);
        service.subscribeNotification(jwt, city, weather);
        return ResponseEntity.ok("Successfully subscribed");
    }

    @PostMapping("/unsubscribe")
    public ResponseEntity<String> unsubscribeNotification(@RequestHeader("Authorization") @NonNull String authorizationHeader,
                                                          @RequestParam("cityName") @NonNull String city,
                                                          @RequestParam("weather") @NonNull String weather) {
        String jwt = authorizationHeader.substring(7);
        service.unsubscribeNotification(jwt, city, weather);
        return ResponseEntity.ok("Successfully unsubscribed");
    }

    @PostMapping("/unsubscribe/all")
    public ResponseEntity<String> unsubscribeAllNotifications(@RequestHeader("Authorization") @NonNull String authorizationHeader,
                                                              @RequestParam("cityName") @NonNull String city,
                                                              @RequestParam("weather") @NonNull String weather) {
        String jwt = authorizationHeader.substring(7);
        service.unsubscribeAllNotifications(jwt);
        return ResponseEntity.ok("Successfully unsubscribed from all notifications");
    }


}
