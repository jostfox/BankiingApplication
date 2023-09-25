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

    @Autowired
    ClientService clientService;

    @Override
    public List<Transaction> getAll() {
        return transactionRepository.findAll();
    }

    @Override
    public Transaction getById(Long id) {
        return transactionRepository.findById(id).orElseThrow(() ->
                new ItemNotFoundException(String.format("Transaction with id %d not found", id)));
    }

    @Override
    public Transaction transfer(String creditIban, String debitIban, BigDecimal amount) {
        Account accountCredit = accountService.getClientAccountByIban(creditIban);
        Account accountDebit = accountService.getByIban(debitIban);

        if (accountCredit.getBalance().subtract(amount)
                .compareTo(new BigDecimal("0")) < 0) {
            throw new NotEnoughFundsException(String.format("Not enough funds on account %s",
                    accountCredit.getIban()));
        }

        accountCredit.setBalance(accountCredit.getBalance().subtract(amount));
        accountDebit.setBalance(accountDebit.getBalance().add(amount));

        Transaction transaction = new Transaction(accountCredit, accountDebit, TransactionType.SUCCESS
                , amount,
                "description", new Timestamp(System.currentTimeMillis()));

        return transactionRepository.save(transaction);
    }

    @Override
    public void delete(Long id) {
        transactionRepository.delete(getById(id));
    }
}
