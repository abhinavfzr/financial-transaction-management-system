package com.ftms.transactionservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ftms.transactionservice.model.Account;
import com.ftms.transactionservice.model.Transaction;
import com.ftms.transactionservice.service.TransactionProducer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.*;

@SpringJUnitConfig
public class TransactionProducerTest {

    @Mock
    private KafkaTemplate<String, String> kafkaTemplate;

    @InjectMocks
    private TransactionProducer transactionProducer;

    @Mock
    private ObjectMapper objectMapper;
    private Transaction transaction;
    private String transactionJson;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        transaction = new Transaction();
        transaction.setId(1L);
        transaction.setAccountId(1L);
        transaction.setType("DEBIT");
        transaction.setAmount(100.0);
        transaction.setStatus("PENDING");
        transactionJson = String.valueOf(transaction);
    }


}
