package org.example.service;

import org.example.entity.Account;

import java.util.List;

public interface AccountService {

    public List<Account> getAll();

    public Account getById(Long id);

    public Account create(Account account);

    public void remove(Long id);

    public Account update(Long id);


}
