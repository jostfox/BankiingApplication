package org.example.service;

import org.example.entity.Transaction;

import java.util.List;

public interface TransactionService {

    public List<Transaction> getAll();

    public Transaction newTransaction(Transaction transaction);
}
