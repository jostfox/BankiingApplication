package org.example.converter;

import org.example.dto.AccountCreateDto;
import org.example.dto.AccountDto;
import org.example.entity.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountConverter implements Converter<Account, AccountDto, AccountCreateDto> {
    @Override
    public AccountDto toDto(Account account) {
        return new AccountDto(account.getId(), account.getIban());
    }

//    @Override
//    public AccountDto toDtoIn(Account account) {
//        return null;
//    }

    @Override
    public Account toEntity(AccountCreateDto account) {
        return new Account(account.getId(), account.getClient().getId(), account.getIban(),
                account.getName(), account.getType(), account.getStatus(),
                account.getBalance(), account.getCurrency(), account.getCreatedAt(),
                account.getUpdatedAt());
    }
}
