package com.ftms.transactionservice;

import com.ftms.transactionservice.model.Transaction;
import com.ftms.transactionservice.repository.TransactionRepository;
import com.ftms.transactionservice.service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private TransactionService transactionService;

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

//    @Test
//    void testCreateTransaction() {
//        when(transactionRepository.save(any(Transaction.class))).thenReturn(transaction);
//
//        Transaction createdTransaction = transactionService.createTransaction(transaction);
//        assertEquals(transaction.getType(), createdTransaction.getType());
//        verify(transactionRepository, times(1)).save(transaction);
//    }

    @Test
    void testGetTransactionById() {
        when(transactionRepository.findById(1L)).thenReturn(Optional.of(transaction));

        Optional<Transaction> fetchedTransaction = transactionService.getTransactionById(1L);
        assertEquals(transaction.getType(), fetchedTransaction.get().getType());
        verify(transactionRepository, times(1)).findById(1L);
    }
}
