package org.example.controller;

import org.example.converter.Converter;
import org.example.dto.AccountCreateDto;
import org.example.dto.AccountDto;
import org.example.entity.Account;
import org.example.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private Converter<Account, AccountDto, AccountCreateDto> accountConverter;

    @GetMapping
    List<AccountDto> getAll() {
        return accountService.getAll().stream()
                .map(account -> accountConverter.toDto(account))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    AccountDto getById(@PathVariable("id") Long id){
        return accountConverter.toDto(accountService.getById(id));
    }

    @PostMapping
    ResponseEntity<AccountDto> createAccount(@RequestBody AccountCreateDto account) {
        return ResponseEntity.ok(accountConverter
                .toDto(accountService.create(accountConverter.toEntity(account))));
    }

    @DeleteMapping("/{id}")
    public void remove (@PathVariable("id") Long id){
        accountService.remove(id);
    }
}
