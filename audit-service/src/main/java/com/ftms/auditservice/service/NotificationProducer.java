package com.ftms.auditservice.service;

import com.ftms.auditservice.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationProducer {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void sendMessage(Transaction transaction) {
        kafkaTemplate.send("notification-topic", transaction);
    }
}
