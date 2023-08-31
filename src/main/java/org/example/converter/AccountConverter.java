package org.example.converter;

import org.example.dto.AccountDto;
import org.example.entity.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountConverter implements Converter<Account, AccountDto> {
    @Override
    public AccountDto toDto(Account account) {
        return new AccountDto(account.getIban(), account.getStatus());
    }

    @Override
    public Account toEntity(AccountDto account) {
        return new Account(account.getIban(), null, account.getName(),
                account.getType(), account.getStatus(), 0,
                account.getCurrency(), account.getCreatedAt(), account.getUpdatedAt());
    }
}
