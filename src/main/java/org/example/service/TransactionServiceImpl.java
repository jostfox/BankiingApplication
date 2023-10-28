package org.example.service;

import com.ritaja.xchangerate.api.CurrencyNotSupportedException;
import com.ritaja.xchangerate.endpoint.EndpointException;
import com.ritaja.xchangerate.service.ServiceException;
import com.ritaja.xchangerate.storage.StorageException;
import org.example.entity.Account;
import org.example.entity.Transaction;
import org.example.enums.TransactionType;
import org.example.exceptions.ItemNotFoundException;
import org.example.exceptions.NotEnoughFundsException;

import org.example.repositories.TransactionRepository;
import org.example.service.utility.CustomCurrencyConverter;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Service("transactionService")
public class TransactionServiceImpl implements TransactionService {

    private  final TransactionRepository transactionRepository;
    private  final AccountService accountService;
    private final CustomCurrencyConverter customCurrencyConverter;

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository,
                                  @Qualifier("accountService") AccountService accountService,
                                  CustomCurrencyConverter customCurrencyConverter) {
        this.transactionRepository = transactionRepository;
        this.accountService = accountService;
        this.customCurrencyConverter = customCurrencyConverter;
    }



    @Override
    public List<Transaction> getAll() {
        return transactionRepository.findAll();
    }

    @Override
    public Transaction getById(Long id) {
        return transactionRepository.findById(id).orElseThrow(() ->
                new ItemNotFoundException(String.format("Transaction with id %d not found", id)));
    }

    @Transactional
    @Override
    public Transaction transfer(String creditIban, String debitIban, BigDecimal amount) throws CurrencyNotSupportedException, EndpointException, ServiceException, JSONException, StorageException {
        Account accountCredit = accountService.getClientAccountByIban(creditIban);
        Account accountDebit = accountService.getByIban(debitIban);

        if (accountCredit.getBalance().subtract(amount)
                .compareTo(new BigDecimal("0")) < 0) {
            throw new NotEnoughFundsException(String.format("Not enough funds on account %s",
                    accountCredit.getIban()));
        }

        BigDecimal convertedAmount = accountCredit.getCurrency().equals(accountDebit.getCurrency())
                ? amount : customCurrencyConverter.convertedAmount(accountDebit, amount);
        accountCredit.setBalance(accountCredit.getBalance().subtract(amount));
        accountDebit.setBalance(accountDebit.getBalance().add(convertedAmount));

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
