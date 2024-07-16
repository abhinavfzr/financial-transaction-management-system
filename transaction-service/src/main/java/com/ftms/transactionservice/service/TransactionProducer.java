package com.ftms.transactionservice.service;

import com.ftms.transactionservice.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class TransactionProducer {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void sendMessage(Transaction transaction) {
        kafkaTemplate.executeInTransaction(operations -> {
            operations.send("transaction-topic", transaction);
            return true;
        });
    }
}
