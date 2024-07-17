package com.ftms.auditservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ftms.auditservice.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public void sendMessage(Transaction transaction) {
            String transactionJson = null;
            try {
                transactionJson = objectMapper.writeValueAsString(transaction);
                kafkaTemplate.send("notification-topic", transactionJson);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
         }
    }

