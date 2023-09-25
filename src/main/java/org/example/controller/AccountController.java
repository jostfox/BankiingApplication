package org.example.controller;

import org.example.converter.Converter;
import org.example.dto.AccountDto;
import org.example.entity.Account;
import org.example.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private Converter<Account, AccountDto> accountConverter;

    @GetMapping
    @PreAuthorize("hasAuthority('permission:admin')")
    List<AccountDto> getAll() {
        return accountService.getAll().stream()
                .map(account -> accountConverter.toDto(account))
                .collect(Collectors.toList());
    }

    @GetMapping("/{iban}")
    @PreAuthorize("hasAuthority('permission:admin')")
    AccountDto getByIban(@PathVariable("iban") String iban){
        return accountConverter.toDto(accountService.getByIban(iban));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('permission:user')")
    ResponseEntity<AccountDto> create(@RequestBody AccountDto account) {
        return ResponseEntity.ok(accountConverter
                .toDto(accountService.create(accountConverter.toEntity(account))));
    }

    @GetMapping("/{account}/balance")
    @PreAuthorize("hasAuthority('permission:user')")
    BigDecimal checkBalance(@PathVariable("account") String iban){
        return accountService.checkBalance(iban);
    }

    @PostMapping("/{iban}/topup/{amount}")
    @PreAuthorize("hasAuthority('permission:user')")
    ResponseEntity <BigDecimal> topUp(@PathVariable("iban") String iban,
                      @PathVariable("amount") BigDecimal amount){
        return ResponseEntity.ok(accountService.topUp(iban,
                amount));
    }

    @PostMapping("/{iban}/withdraw/{amount}")
    @PreAuthorize("hasAuthority('permission:user')")
    ResponseEntity <BigDecimal> withdraw(@PathVariable("iban") String iban,
                         @PathVariable("amount") BigDecimal amount){
        return ResponseEntity.ok(accountService.withdraw(iban, amount));

    }

    @DeleteMapping("/{iban}/close")
    @PreAuthorize("hasAuthority('permission:user')")
    public void close(@PathVariable("iban") String iban){
        accountService.close(iban);
    }
}
