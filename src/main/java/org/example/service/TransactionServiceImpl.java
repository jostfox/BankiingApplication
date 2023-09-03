package org.example.service;

import org.example.entity.Account;
import org.example.entity.Client;
import org.example.entity.Transaction;
import org.example.exceptions.ItemNotFoundException;
import org.example.exceptions.NotEnoughFundsException;
import org.example.repositories.AccountRepository;
import org.example.repositories.TransactionRepository;
import org.example.service.handler.FindAccountByIdHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

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
    public void transfer(Long accountOneId, Long accountTwoId, BigDecimal amount) {
        Account accountFrom = accountByIdHandler.findByIdHandledWithException(accountOneId, accountRepository);
        Account accountTo = accountByIdHandler.findByIdHandledWithException(accountTwoId, accountRepository);

        if (accountFrom.getBalance().subtract(amount)
                .compareTo(new BigDecimal("0")) < 0){
            throw new NotEnoughFundsException(String.format("Not enough funds on account %s",
                     accountFrom.getIban()));
        }

        accountFrom.setBalance(accountFrom.getBalance().subtract(amount));
        accountRepository.save(accountFrom);
        accountTo.setBalance(accountTo.getBalance().add(amount));
        accountRepository.save(accountTo);

//        Transaction transaction = new Transaction(1L, accountFrom, accountTo, amount,
//                "description", 1, LocalDateTime.now());
//
//        transactionRepository.save(transaction);
    }
}
