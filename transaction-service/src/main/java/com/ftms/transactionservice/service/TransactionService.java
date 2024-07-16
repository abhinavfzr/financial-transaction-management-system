package com.ftms.transactionservice.service;;

import com.ftms.transactionservice.exception.InsufficientBalanceException;
import com.ftms.transactionservice.model.Transaction;
import com.ftms.transactionservice.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private  TransactionProducer transactionProducer;
    @Autowired
    private AccountService accountService;

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public Optional<Transaction> getTransactionById(Long id) {
        return transactionRepository.findById(id);
    }
    @Transactional
    public Transaction createTransaction(Transaction transaction) {
        // Initialize transaction status as PENDING
        transaction.setStatus("PENDING");
        // Check account balance and process the transaction
        if (!accountService.processTransaction(transaction)) {
            throw new InsufficientBalanceException("Insufficient balance");
        }
        Transaction savedTransaction = transactionRepository.save(transaction);
        transactionProducer.sendMessage(savedTransaction);
        return savedTransaction;
    }

}
