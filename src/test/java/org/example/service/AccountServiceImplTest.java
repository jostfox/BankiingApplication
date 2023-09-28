package org.example.service;

import org.example.entity.Account;
import org.example.repositories.AccountRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {

    @InjectMocks
    private AccountServiceImpl accountService;

    @Mock
    private AccountRepository accountRepository;

    @Test
    void getByIban() {
        Account account = new Account();
        account.setIban("DE1351351513515358");
        Mockito
                .when(accountRepository.findById(account.getIban()))
                .thenReturn(Optional.of(account));

        Account testingAccount = accountService.getByIban("DE1351351513515358");
        assertEquals("DE1351351513515358", testingAccount.getIban());
    }

    @Test
    void checkBalance() {
        Account account = new Account();
        account.setBalance(BigDecimal.valueOf(1500.25));
        account.setIban("DE1351351513515358");
        Mockito
                .when(accountRepository.findById(account.getIban()))
                .thenReturn(Optional.of(account));

        Account testingAccount = accountService.getByIban("DE1351351513515358");
        assertEquals(BigDecimal.valueOf(1500.25), testingAccount.getBalance());
    }
}