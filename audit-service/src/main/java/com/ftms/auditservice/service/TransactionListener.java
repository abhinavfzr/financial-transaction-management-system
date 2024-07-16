package com.ftms.auditservice.service;
import com.ftms.auditservice.model.Transaction;
import com.ftms.auditservice.repository.AuditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class TransactionListener {

    @Autowired
    private AuditRepository transactionRepository;
    @Autowired
    private  NotificationProducer notificationProducer;

    @KafkaListener(topics = "transaction-topic", groupId = "audit-group")
    public void consume(Transaction transaction) {
        if (!transactionRepository.existsById(transaction.getId())) {
            transactionRepository.save(transaction);
        }
        updateTransactionStatus(transaction);
    }

    private void updateTransactionStatus(Transaction transaction) {
        transaction.setStatus("COMPLETED");
        transactionRepository.save(transaction);
        notificationProducer.sendMessage(transaction);
    }
}
