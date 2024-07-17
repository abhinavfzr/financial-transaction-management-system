package com.ftms.auditservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ftms.auditservice.model.Transaction;
import com.ftms.auditservice.service.TransactionListener;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.mockito.Mockito.*;

@SpringJUnitConfig
public class AuditServiceTest {

    @InjectMocks
    private TransactionListener auditListener;

    @Mock
    private ObjectMapper objectMapper;

    private Transaction transaction;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        transaction = new Transaction();
        transaction.setId(1L);
        transaction.setAccountId(1L);
        transaction.setType("DEBIT");
        transaction.setAmount(100.0);
        transaction.setStatus("PENDING");
    }


}
