package org.example.controller;

import com.ritaja.xchangerate.api.CurrencyNotSupportedException;
import com.ritaja.xchangerate.endpoint.EndpointException;
import com.ritaja.xchangerate.service.ServiceException;
import com.ritaja.xchangerate.storage.StorageException;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.converter.Converter;
import org.example.dto.TransactionDto;
import org.example.entity.Transaction;
import org.example.service.TransactionService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Transaction controller",
        description = "Provides transactions and CRUD operations with them")
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

    @SecurityRequirement(name = "basicauth")
    @GetMapping
    @PreAuthorize("hasAuthority('permission:user')")
    public List<TransactionDto> getAll() {
        return transactionService.getAll().stream().map(transactionConverter::toDto).collect(Collectors.toList());
    }

    @SecurityRequirement(name = "basicauth")
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('permission:admin')")
    public TransactionDto getById(@PathVariable("id") Long id) {
        return transactionConverter.toDto(transactionService.getById(id));
    }

    @SecurityRequirement(name = "basicauth")
    @PutMapping("/{accountFrom}/{accountTo}/{amount}")
    @PreAuthorize("hasAuthority('permission:user')")
    public TransactionDto transfer(@PathVariable("accountFrom") String accountFrom,
                                   @PathVariable("accountTo") String accountTo, @PathVariable(
                                           "amount") BigDecimal amount) throws CurrencyNotSupportedException, EndpointException, ServiceException, JSONException, StorageException {
        Transaction transaction = transactionService.transfer(accountFrom, accountTo, amount);

        return transactionConverter.toDto(transaction);
    }

    @SecurityRequirement(name = "basicauth")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('permission:admin')")
    public void remove(@PathVariable("id") Long id) {
        transactionService.delete(id);
    }
}