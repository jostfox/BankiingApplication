package org.example.service;

import org.example.entity.Account;
import org.example.entity.Client;
import org.example.entity.Transaction;
import org.example.exceptions.ItemNotFoundException;
import org.example.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    AccountService accountService;

    @Override
    public List<Transaction> getAll() {
        return transactionRepository.findAll();
    }

    @Override
    public Transaction getById(Long id) {
        Transaction transaction = transactionRepository.findById(id).orElse(null);
        if (transaction == null){
            throw new ItemNotFoundException(String.format("Transaction with id %d not found", id));
        }
        return transactionRepository.getReferenceById(id);
    }

    @Override
    public Transaction create(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public void transfer(Long accountOneId, Long accountTwoId, double amount) {
        Account accountFrom = accountService.getById(accountOneId);
        Account accountTo = accountService.getById(accountTwoId);

        if (accountFrom.getBalance() - amount < 0){
            throw new IllegalArgumentException("Not enough amount on account");
        }

        Transaction transaction = new Transaction(accountFrom, accountTo, amount,
                "description", 1, null);
        transactionRepository.save(transaction);
    }
}