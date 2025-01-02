package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
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

@Tag(name = "Account controller",
        description = "Provides basic banking operations with the client`s accounts"
)
@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;
    private final Converter<Account, AccountDto> accountConverter;

    @Autowired
    public AccountController(AccountService accountService, Converter<Account, AccountDto> accountConverter) {
        this.accountService = accountService;
        this.accountConverter = accountConverter;
    }

    @Operation(summary = "List of accounts",
            description = "Get a list of all accounts")
    @SecurityRequirement(name = "basicauth")
    @GetMapping
    @PreAuthorize("hasAuthority('permission:admin')")
    List<AccountDto> getAll() {
        return accountService.getAll().stream()
                .map(accountConverter::toDto)
                .collect(Collectors.toList());
    }

    @Operation(summary = "List of specified clients accounts",
    description = "Get a list of accounts of specified client")
    @SecurityRequirement(name = "basicauth")
    @GetMapping("/{firstName}/{lastName}")
    @PreAuthorize("hasAuthority('permission:admin')")
    List<AccountDto> getClientsAccounts(@PathVariable String firstName, @PathVariable String lastName) {
        return accountService.getClientsAccounts(firstName, lastName)
                .stream().map(accountConverter::toDto)
                .collect(Collectors.toList());
    }

    @Operation(summary = "Get account by IBAN",
            description = "Get an account by specified IBAN")
    @SecurityRequirement(name = "basicauth")
    @GetMapping("/{iban}")
    @PreAuthorize("hasAuthority('permission:admin')")
    AccountDto getByIban(@PathVariable("iban") String iban){
        return accountConverter.toDto(accountService.getByIban(iban));
    }

    @Operation(summary = "Create an account",
            description = "Create a new account")
    @SecurityRequirement(name = "basicauth")
    @PostMapping
    @PreAuthorize("hasAuthority('permission:user')")
    ResponseEntity<AccountDto> create(@RequestBody AccountDto account) {
        return ResponseEntity.ok(accountConverter
                .toDto(accountService.create(accountConverter.toEntity(account))));
    }

    @Operation(summary = "Check account`s balance",
            description = "Check the balance by specified IBAN")
    @SecurityRequirement(name = "basicauth")
    @GetMapping("/{account}/balance")
    @PreAuthorize("hasAuthority('permission:user')")
    BigDecimal checkBalance(@PathVariable("IBAN") String iban){
        return accountService.checkBalance(iban);
    }

    @Operation(summary = "Deposit the account",
            description = "Deposit the account by specified IBAN and amount")
    @SecurityRequirement(name = "basicauth")
    @PostMapping("/{iban}/topup/{amount}")
    @PreAuthorize("hasAuthority('permission:user')")
    ResponseEntity <BigDecimal> topUp(@PathVariable("iban") String iban,
                      @PathVariable("amount") BigDecimal amount){
        return ResponseEntity.ok(accountService.topUp(iban,
                amount));
    }

    @Operation(summary = "Cash withdrawal",
            description = "Cash withdrawal by specified IBAN and amount")
    @SecurityRequirement(name = "basicauth")
    @PostMapping("/{iban}/withdraw/{amount}")
    @PreAuthorize("hasAuthority('permission:user')")
    ResponseEntity <BigDecimal> withdraw(@PathVariable("iban") String iban,
                         @PathVariable("amount") BigDecimal amount){
        return ResponseEntity.ok(accountService.withdraw(iban, amount));
    }

    @Operation(summary = "Close an account",
            description = "Close an account by specified IBAN")
    @SecurityRequirement(name = "basicauth")
    @DeleteMapping("/{iban}/close")
    @PreAuthorize("hasAuthority('permission:user')")
    public void close(@PathVariable("iban") String iban){
        accountService.close(iban);
    }
}
