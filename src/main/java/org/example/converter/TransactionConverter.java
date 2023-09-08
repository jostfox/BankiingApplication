package org.example.converter;

import org.example.dto.TransactionDto;
import org.example.entity.Transaction;
import org.springframework.stereotype.Component;

@Component
public class TransactionConverter implements Converter<Transaction, TransactionDto> {

    @Override
    public TransactionDto toDto(Transaction transaction) {
        return new TransactionDto(transaction.getId(),
                transaction.getDebitAccount().getIban(),
                transaction.getCreditAccount().getIban(),
                transaction.getAmount(),
                transaction.getDescription(),
                transaction.getType(),
                transaction.getCreatedAt());
    }

    @Override
    public Transaction toEntity(TransactionDto transaction) {
        throw new UnsupportedOperationException();
    }
}
