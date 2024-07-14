package com.ftms.auditservice.service;

import com.ftms.auditservice.model.Audit;
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

    public Audit saveAudit(String action, String details) {
        Audit audit = new Audit(action, details, LocalDateTime.now());
        return auditRepository.save(audit);
    }

    @KafkaListener(topics = "audit-topic", groupId = "audit-group")
    public void listenToAuditTopic(String message) {
        // Assuming the message contains action and details separated by a comma
        String[] parts = message.split(",");
        if (parts.length == 2) {
            saveAudit(parts[0], parts[1]);
        }
    }
    public List<Audit> findAll() {
        return auditRepository.findAll();
    }
}