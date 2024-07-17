package com.ftms.accountservice;

import com.ftms.accountservice.model.Account;
import com.ftms.accountservice.repository.AccountRepository;
import com.ftms.accountservice.service.AccountService;
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
public class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountService accountService;

    private Account account;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        account = new Account();
        account.setId(1L);
        account.setName("John Doe");
        account.setEmail("john.doe@example.com");
        account.setBalance(1000.0);
    }

    @Test
    void testCreateAccount() {
        when(accountRepository.save(any(Account.class))).thenReturn(account);

        Account createdAccount = accountService.createAccount(account);
        assertEquals(account.getName(), createdAccount.getName());
        verify(accountRepository, times(1)).save(account);
    }

    @Test
    void testGetAccountById() {
        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));

        Optional<Account> fetchedAccount = accountService.getAccountById(1L);
        assertEquals(account.getName(), fetchedAccount.get().getName());
        verify(accountRepository, times(1)).findById(1L);
    }
}
