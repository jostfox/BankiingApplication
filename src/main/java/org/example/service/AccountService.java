package org.example.service;

import org.example.entity.Account;

import java.math.BigDecimal;
import java.util.List;

public interface AccountService {

    List<Account> getAll();

    Account getByIban(String iban);
    List<Account> getClientsAccounts(String lastName, String firstName);

    Account create(Account account);

    BigDecimal checkBalance(String iban);

    void close(String iban);

    BigDecimal topUp(String iban, BigDecimal amount);

    BigDecimal withdraw(String iban, BigDecimal amount);

    Account getClientAccountByIban(String iban);
}
