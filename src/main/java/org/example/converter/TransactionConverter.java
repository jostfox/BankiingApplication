package org.example.converter;

import org.example.dto.TransactionCreateDto;
import org.example.dto.TransactionDto;
import org.example.entity.Transaction;
import org.springframework.stereotype.Component;

@Component
public class TransactionConverter implements Converter <Transaction, TransactionDto, TransactionCreateDto> {

    @Override
    public TransactionDto toDto(Transaction transaction) {
        return new TransactionDto(transaction.getId(), transaction.getCreditAccount(),
                transaction.getDebitAccount(), transaction.getAmount(), transaction.getDescription());
    }

    @Override
    public Transaction toEntity(TransactionCreateDto transactionCreateDto) {
        return new Transaction(transactionCreateDto.getCreditAccount(),
                transactionCreateDto.getDebitAccount(), transactionCreateDto.getAmount(),
                transactionCreateDto.getDescription(), transactionCreateDto.getType(),
                transactionCreateDto.getCreatedAt());
    }
}
