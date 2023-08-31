package org.example.service.handler;

import org.example.entity.Account;
import org.example.exceptions.ItemNotFoundException;
import org.example.repositories.AccountRepository;
import org.springframework.stereotype.Component;

@Component
public class FindAccountByIdHandler implements FindById<Account, AccountRepository> {

    @Override
    public Account findByIdHandledWithException(Long id, AccountRepository accountRepository) {
        return null;

    }
}
