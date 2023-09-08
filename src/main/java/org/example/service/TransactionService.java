package org.example.service;

import org.example.entity.Transaction;

import java.math.BigDecimal;
import java.util.List;

public interface TransactionService {

    List<Transaction> getAll();

    Transaction getById(Long id);

    Transaction transfer(String accountOneId, String accountTwoId, BigDecimal amount);

    void delete(Long id);
}