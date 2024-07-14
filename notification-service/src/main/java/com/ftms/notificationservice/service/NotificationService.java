package com.ftms.notificationservice.service;

import com.ftms.notificationservice.model.Notification;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @KafkaListener(topics = "notification-topic", groupId = "notification-group")
    public void listenToNotificationTopic(Notification notification) {
        // Process the notification
        System.out.println("Received notification: " + notification.getMessage() + " for recipient: " + notification.getRecipient());
    }
}