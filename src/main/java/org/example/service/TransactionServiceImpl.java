package org.example.service;

import org.example.entity.Account;
import org.example.entity.Transaction;
import org.example.enums.TransactionType;
import org.example.exceptions.ItemNotFoundException;
import org.example.exceptions.NotEnoughFundsException;

import org.example.repositories.TransactionRepository;
import org.example.service.handler.FindAccountByIdHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    FindAccountByIdHandler accountByIdHandler;

    @Override
    public List<Transaction> getAll() {
        return transactionRepository.findAll();
    }

    @Override
    public Transaction getById(Long id) {
        Transaction transaction = transactionRepository.findById(id).orElse(null);
        if (transaction == null) {
            throw new ItemNotFoundException(String.format("Transaction with id %d not found", id));
        }
        return transactionRepository.getReferenceById(id);
    }

    @Override
    public Transaction transfer(String debetIban, String creditIban, BigDecimal amount) {
        Account accountDebit = accountService.getByIban(debetIban);
        Account accountCredit = accountService.getByIban(creditIban);

        if (accountDebit.getBalance().subtract(amount)
                .compareTo(new BigDecimal("0")) < 0) {
            throw new NotEnoughFundsException(String.format("Not enough funds on account %s",
                    accountDebit.getIban()));
        }

        accountDebit.setBalance(accountDebit.getBalance().subtract(amount));
        accountCredit.setBalance(accountCredit.getBalance().add(amount));

        Transaction transaction = new Transaction(accountDebit, accountCredit, TransactionType.SUCCESS, amount,
                "description", new Timestamp(System.currentTimeMillis()));

        return transactionRepository.save(transaction);
    }

    @Override
    public void delete(Long id) {
        transactionRepository.delete(getById(id));
    }
}
