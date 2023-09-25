package org.example.service;

import org.example.entity.Account;

import java.math.BigDecimal;
import java.util.List;

public interface AccountService {

    public List<Account> getAll();

    public Account getByIban(String iban);

    public Account create(Account account);

    public BigDecimal checkBalance(String iban);

    public void close(String iban);

    public BigDecimal topUp(String iban, BigDecimal amount);

    public BigDecimal withdraw(String iban, BigDecimal amount);

    public Account getClientAccountByIban(String iban);


}
