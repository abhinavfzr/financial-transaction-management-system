package com.ftms.notificationservice.controller;

import com.ftms.notificationservice.model.Notification;
import com.ftms.notificationservice.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @PostMapping
    public void sendNotification(@RequestBody Notification notification) {
        // In a real application, we would send this to Kafka
        // Here we just simulate the receipt of the notification
        notificationService.listenToNotificationTopic(notification);
    }
}