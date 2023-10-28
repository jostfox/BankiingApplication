package org.example.service;

import com.ritaja.xchangerate.api.CurrencyNotSupportedException;
import com.ritaja.xchangerate.endpoint.EndpointException;
import com.ritaja.xchangerate.service.ServiceException;
import com.ritaja.xchangerate.storage.StorageException;
import org.example.entity.Account;
import org.example.entity.Transaction;
import org.example.repositories.TransactionRepository;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
class TransactionServiceImplTest {

    @InjectMocks
    private TransactionServiceImpl transactionService;

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private AccountServiceImpl accountService;

    @Test
    void getById() {
        Transaction transaction = new Transaction();
        transaction.setId(2L);
        Mockito.when(transactionRepository.findById(transaction.getId())).thenReturn(Optional.of(transaction));

        Transaction testTransaction = transactionService.getById(2L);
        assertEquals(2L, testTransaction.getId());
    }

    @Test
    public void testTransferWithSufficientFunds() throws CurrencyNotSupportedException, EndpointException, ServiceException, JSONException, StorageException {
        Account mockCreditAccount = new Account();
        mockCreditAccount.setBalance(BigDecimal.valueOf(1000));
        Mockito
                .when(accountService.getByIban(anyString())).thenReturn(mockCreditAccount);

        Account mockDebitAccount = new Account();
        mockDebitAccount.setBalance(BigDecimal.valueOf(0));
        Mockito
                .when(accountService.getByIban(anyString())).thenReturn(mockDebitAccount);

        BigDecimal transferAmount = new BigDecimal("500");
        Transaction resultTransaction = transactionService.transfer("creditIban", "debitIban",
                transferAmount);

        assertEquals(new BigDecimal("500"), mockCreditAccount.getBalance());
        assertEquals(new BigDecimal("500"), mockDebitAccount.getBalance());

        Mockito.verify(transactionRepository).save(resultTransaction);
    }
}

