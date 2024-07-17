package com.ftms.auditservice.service;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    @Autowired
    private ObjectMapper objectMapper;

    @KafkaListener(topics = "transaction-topic", groupId = "audit-group")
    public void listen(String transactionJson) {
        try {
            Transaction transaction = objectMapper.readValue(transactionJson, Transaction.class);
            if (!transactionRepository.existsById(transaction.getId())) {
                transactionRepository.save(transaction);
            }
            updateTransactionStatus(transaction);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateTransactionStatus(Transaction transaction) {
        transaction.setStatus("COMPLETED");
        transactionRepository.save(transaction);
        System.out.println("Sending notification for transaction: " + transaction.toString());
        notificationProducer.sendMessage(transaction);
    }
}
