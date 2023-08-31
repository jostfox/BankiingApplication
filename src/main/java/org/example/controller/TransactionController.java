package org.example.controller;

import org.example.converter.Converter;
import org.example.dto.TransactionDto;
import org.example.entity.Transaction;
import org.example.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public List<TransactionDto> getAll(){
        return transactionService.getAll().stream().
                map(transactionConverter::toDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public TransactionDto getById(@PathVariable("id") Long id){
        return transactionConverter.toDto(transactionService.getById(id));
    }

    @PostMapping ("/{accountFromId}/{accountToId}/{amount}")
    public void transfer(@PathVariable("accountFromId") Long accountFrom,
                         @PathVariable("accountToId") Long accountTo,
                         @PathVariable("amount") double amount){
        transactionService.transfer(accountFrom, accountTo, amount);

        }

}
