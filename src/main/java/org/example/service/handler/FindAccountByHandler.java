package org.example.service.handler;

import org.example.entity.Account;
import org.example.exceptions.ItemNotFoundException;
import org.example.repositories.AccountRepository;
import org.springframework.stereotype.Component;

@Component
public class FindAccountByHandler implements FindById<Account, AccountRepository> {

    @Override
    public Account findByIdHandledWithException(Long id, AccountRepository accountRepository) {
        Account account = accountRepository.findById(id).orElse(null);
        if (account == null) {
            throw new ItemNotFoundException(String.format("Account with id %d not found", id));
        }
        return account;

    }
}
