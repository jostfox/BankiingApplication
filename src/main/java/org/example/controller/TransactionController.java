package org.example.controller;

import org.example.entity.Transaction;
import org.example.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @GetMapping
    List<Transaction> getAll() {
        return transactionService.getAll();
    }

    @PostMapping
    ResponseEntity<Transaction> newTransaction(@RequestBody Transaction transaction) {
        return ResponseEntity.ok(transactionService.newTransaction(transaction));
    }
}
