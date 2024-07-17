package com.ftms.accountservice.service;

import com.ftms.accountservice.model.Account;
import com.ftms.accountservice.model.Transaction;
import com.ftms.accountservice.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Optional<Account> getAccountById(Long id) {
        return accountRepository.findById(id);
    }

    public Account createAccount(Account account) {
        Account savedAccount = accountRepository.save(account);
        return savedAccount;
    }

    public Account updateAccount(Long id, Account accountDetails) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account not found"));
        account.setName(accountDetails.getName());
        account.setEmail(accountDetails.getEmail());
        return accountRepository.save(account);
    }
    protected Account updateAccountBalance(Transaction transaction) {
        Account account = accountRepository.findById(transaction.getAccountId()).orElseThrow(() -> new RuntimeException("Account not found"));
        if ("DEBIT".equals(transaction.getType()) && account.getBalance() + transaction.getAmount() >= 0) {
            account.setBalance(account.getBalance() + transaction.getAmount());
        } else if ("CREDIT".equals(transaction.getType())) {
            account.setBalance(account.getBalance() + transaction.getAmount());
        }
        return accountRepository.save(account);
    }


    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }
}
