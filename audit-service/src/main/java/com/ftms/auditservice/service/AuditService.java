package com.ftms.auditservice.service;

import com.ftms.auditservice.model.Transaction;
import com.ftms.auditservice.repository.AuditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AuditService {

    @Autowired
    private AuditRepository auditRepository;

    public List<Transaction> findAll() {
        return auditRepository.findAll();
    }
}