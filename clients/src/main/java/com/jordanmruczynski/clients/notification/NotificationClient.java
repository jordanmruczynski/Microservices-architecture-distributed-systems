package com.jordanmruczynski.clients.notification;

import com.jordanmruczynski.clients.fraud.FraudCheckResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(
        value = "notification",
        path = "api/v1/notification"
)
public interface NotificationClient {

    @PostMapping
    void sendNotification(NotificationRequest notificationRequest);
}
