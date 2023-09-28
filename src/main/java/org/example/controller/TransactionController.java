package org.example.controller;

import org.example.converter.Converter;
import org.example.dto.TransactionDto;
import org.example.entity.Transaction;
import org.example.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;
    private final Converter<Transaction, TransactionDto> transactionConverter;

    @Autowired
    public TransactionController(TransactionService transactionService, Converter<Transaction,
            TransactionDto> transactionConverter) {
        this.transactionService = transactionService;
        this.transactionConverter = transactionConverter;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('permission:user')")
    public List<TransactionDto> getAll() {
        return transactionService.getAll().stream().map(transactionConverter::toDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('permission:admin')")
    public TransactionDto getById(@PathVariable("id") Long id) {
        return transactionConverter.toDto(transactionService.getById(id));
    }

    @PutMapping("/{accountFrom}/{accountTo}/{amount}")
    @PreAuthorize("hasAuthority('permission:user')")
    public TransactionDto transfer(@PathVariable("accountFrom") String accountFrom,
                                   @PathVariable("accountTo") String accountTo, @PathVariable(
                                           "amount") BigDecimal amount) {
        Transaction transaction = transactionService.transfer(accountFrom, accountTo, amount);

        return transactionConverter.toDto(transaction);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('permission:admin')")
    public void remove(@PathVariable("id") Long id) {
        transactionService.delete(id);
    }
}