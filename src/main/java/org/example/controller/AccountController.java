package org.example.controller;

import org.example.converter.Converter;
import org.example.dto.AccountDto;
import org.example.entity.Account;
import org.example.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private Converter<Account, AccountDto> accountConverter;

    @GetMapping
    List<AccountDto> getAll() {
        return accountService.getAll().stream()
                .map(account -> accountConverter.toDto(account))
                .collect(Collectors.toList());
    }

    @GetMapping("/{clientId}/{iban}")
    AccountDto getByIban(@PathVariable("clientId") Long clientId,
                         @PathVariable("iban") String iban){
        return accountConverter.toDto(accountService.getByIban(iban));
    }

    @PostMapping
    ResponseEntity<AccountDto> createAccount(@RequestBody AccountDto account) {
        return ResponseEntity.ok(accountConverter
                .toDto(accountService.create(accountConverter.toEntity(account))));
    }

    @GetMapping("/{clientId}/{accountId}/balance")
    BigDecimal checkBalance(@PathVariable("clientId") Long clientId,
                        @PathVariable("accountId") String iban){
        return accountService.checkBalance(clientId, iban);
    }

    @PostMapping("/{clientId}/{accountId}/topup/{amount}")
    public void topUpAccount(@PathVariable("clientId") Long clientId,
                             @PathVariable("accountId") String iban,
                             @PathVariable("amount") BigDecimal amount){
        accountService.topUpAccount(clientId, iban, amount);
    }

    @DeleteMapping("/{clientId}/{iban}/close")
    public void closeAccount(@PathVariable("clientId") Long clientId,
                             @PathVariable("iban") String iban){
        accountService.closeAccount(clientId, iban);
    }
}
