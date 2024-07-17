package com.ftms.transactionservice.service;

import com.ftms.transactionservice.model.Account;
import com.ftms.transactionservice.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private RestTemplate restTemplate;

    private static final String ACCOUNT_SERVICE_URL = "http://localhost:8081/api/accounts";

    public boolean processTransaction(Transaction transaction) {
        Optional<Account> accountOptional = getAccountById(transaction.getAccountId());
        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();
            if ("DEBIT".equals(transaction.getType()) && account.getBalance() + transaction.getAmount() >= 0) {
                return true;
            } else if ("CREDIT".equals(transaction.getType())) {
                return true;
            }
        }
        return false;
    }

    private Optional<Account> getAccountById(Long accountId) {
        return Optional.ofNullable(restTemplate.getForObject(ACCOUNT_SERVICE_URL + "/" + accountId, Account.class));
    }

}
