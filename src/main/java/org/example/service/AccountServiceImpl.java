package org.example.service;

import org.example.entity.Account;
import org.example.entity.Client;
import org.example.exceptions.ItemNotFoundException;
import org.example.repositories.AccountRepository;
import org.example.service.handler.FindAccountByHandler;
import org.example.service.handler.FindById;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private FindById<Account, AccountRepository> findAccountById;

    @Override
    public List<Account> getAll() {
        return accountRepository.findAll();
    }

    @Override
    public Account create(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Account getById(Long id) {
        Account account = accountRepository.findById(id).orElse(null);
        if (account == null){
            throw new ItemNotFoundException(String.format("Account with id %d not found", id));
        }
        return accountRepository.getReferenceById(id);
    }

    @Override
    public void remove(Long id) {
        accountRepository.delete(findAccountById.findByIdHandledWithException(id, accountRepository));
    }

    @Override
    public Account update(Long id) {
        return null;
    }


}
