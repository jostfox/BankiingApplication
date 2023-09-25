package org.example.converter;

import org.example.dto.AccountDto;
import org.example.entity.Account;
import org.example.enums.Status;
import org.example.service.ClientService;
import org.example.service.ClientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Timestamp;


@Component
public class AccountConverter implements Converter<Account, AccountDto> {

    @Autowired
    private ClientServiceImpl clientService;

    @Override
    public AccountDto toDto(Account account) {
        return new AccountDto(account.getIban(), account.getStatus(), account.getBalance().doubleValue());
    }

    @Override
    public Account toEntity(AccountDto account) {
        return new Account(account.getIban(), clientService.getCurrent(), account.getName(),
                account.getType(), Status.ACTIVE, new BigDecimal("0.00"),
                account.getCurrency(), new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis()));
    }
}
