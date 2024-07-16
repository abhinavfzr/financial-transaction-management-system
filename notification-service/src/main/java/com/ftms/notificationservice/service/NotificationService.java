package com.ftms.notificationservice.service;


import com.ftms.transactionservice.model.Transaction;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @KafkaListener(topics = "notification-topic", groupId = "notification-group")
    public void consume(Transaction transaction) {
        // Logic to send notification
        System.out.println("Sending notification for transaction: " + transaction);
        // Example: send email or SMS
    }
}