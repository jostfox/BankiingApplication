package org.example.converter;

import org.example.dto.AccountDto;
import org.example.entity.Account;
import org.example.enums.Status;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Component
public class AccountConverter implements Converter<Account, AccountDto> {
    @Override
    public AccountDto toDto(Account account) {
        return new AccountDto(account.getIban(), account.getStatus(), account.getBalance().doubleValue());
    }

    @Override
    public Account toEntity(AccountDto account) {
        return new Account(account.getIban(), null, account.getName(),
                account.getType(), Status.ACTIVE, new BigDecimal("0.00"),
                account.getCurrency(), new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis()));
    }
}
