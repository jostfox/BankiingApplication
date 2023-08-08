package org.example.service;

import org.example.entity.Account;

import java.util.List;

public interface AccountService {

public List<Account> getAll();

public Account createAccount(Account account);
}
