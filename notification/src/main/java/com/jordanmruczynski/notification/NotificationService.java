package com.jordanmruczynski.notification;

import com.jordanmruczynski.clients.notification.NotificationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public void send(NotificationRequest notificationRequest) {
        notificationRepository.save(
                Notification.builder()
                        .customerId(notificationRequest.customerId())
                        .customerEmail(notificationRequest.customerEmail())
                        .sender("Admin")
                        .message(notificationRequest.message())
                        .createdAt(LocalDateTime.now())
                        .build()
        );
    }

}
