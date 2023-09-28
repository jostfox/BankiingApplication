package org.example.service;

import org.example.entity.Transaction;
import org.example.repositories.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TransactionServiceImplTest {

    @InjectMocks
    private TransactionServiceImpl transactionService;

    @Mock
    private TransactionRepository transactionRepository;

    @Test
    void getById() {
        Transaction transaction = new Transaction();
        transaction.setId(2L);
        Mockito
                .when(transactionRepository.findById(transaction.getId()))
                .thenReturn(Optional.of(transaction));

        Transaction testTransaction = transactionService.getById(2L);
        assertEquals(2L, testTransaction.getId());
    }

    @Test
    void transfer() {

    }
}