package com.ftms.accountservice.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.ftms.accountservice.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class AccountListner {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private  AccountService accountService;

    @KafkaListener(topics = "notification-topic", groupId = "account-group")
    public void listen(String transactionJson) {
        System.out.println("Received in AccountService: " + transactionJson);
        try {
            Transaction transaction = objectMapper.readValue(transactionJson, Transaction.class);
            accountService.updateAccountBalance(transaction);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}