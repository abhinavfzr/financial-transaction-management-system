package com.ftms.transactionservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ftms.transactionservice.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class TransactionProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public void sendMessage(Transaction transaction) {
        kafkaTemplate.executeInTransaction(operations -> {
            String transactionJson = null;
            try {
                transactionJson = objectMapper.writeValueAsString(transaction);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            operations.send("transaction-topic", transactionJson);
            return true;
        });
    }
}
