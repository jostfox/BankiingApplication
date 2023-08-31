package org.example.service;

import org.example.entity.Account;
import org.example.entity.Client;

import java.util.List;

public interface AccountService {

    public List<Account> getAll();

    public Account getByIban(Long clientId, String iban);

    public Account create(Account account);

    public Account update(String iban);

    public double checkBalance(Long clientId, String iban);

    public void closeAccount(Long clientId, String iban);

    public void topUpAccount(Long clientId, String iban, double amount);

    public void withdraw(Long clientId, String iban, double amount);


}
