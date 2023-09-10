package org.example.controller;

import org.example.converter.Converter;
import org.example.dto.TransactionDto;
import org.example.entity.Transaction;
import org.example.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private Converter<Transaction, TransactionDto> transactionConverter;

    @GetMapping
    public List<TransactionDto> getAll() {
        return transactionService.getAll().stream().
                map(transactionConverter::toDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public TransactionDto getById(@PathVariable("id") Long id) {
        return transactionConverter.toDto(transactionService.getById(id));
    }

    @PostMapping("/{accountFrom}/{accountTo}/{amount}")
    public TransactionDto transfer(@PathVariable("accountFrom") String accountFrom,
                                   @PathVariable("accountTo") String accountTo,
                                   @PathVariable("amount") BigDecimal amount) {
        Transaction transaction = transactionService.transfer(accountFrom, accountTo, amount);

        return transactionConverter.toDto(transaction);
    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable("id") Long id) {
        transactionService.delete(id);
    }
}