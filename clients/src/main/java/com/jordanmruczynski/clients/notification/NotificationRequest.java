package com.jordanmruczynski.clients.notification;

public record NotificationRequest(
        Integer customerId,
        String customerName,
        String message,
        String customerEmail
) {
}
