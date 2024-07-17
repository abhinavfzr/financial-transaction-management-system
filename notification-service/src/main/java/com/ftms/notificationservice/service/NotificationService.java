package com.ftms.notificationservice.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.ftms.transactionservice.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @Autowired
    ObjectMapper objectMapper;

    @KafkaListener(topics = "notification-topic", groupId = "notification-group")
    public void listen(String transactionJson) {
          System.out.println("Sending notification for transaction: " + transactionJson);
        // Example: send email or SMS
    }
}