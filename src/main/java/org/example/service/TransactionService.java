package org.example.service;

import org.example.entity.Transaction;

import java.util.List;

public interface TransactionService {

    public List<Transaction> getAll();

    public Transaction getById(Long id);

    public Transaction create(Transaction transaction);

    public void transfer(Long accountOneId, Long accountTwoId, double amount);
}
