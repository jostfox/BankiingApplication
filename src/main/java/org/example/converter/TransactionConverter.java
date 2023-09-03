package org.example.converter;

import org.example.dto.TransactionDto;
import org.example.entity.Transaction;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class TransactionConverter implements Converter <Transaction, TransactionDto> {

    @Override
    public TransactionDto toDto(Transaction transaction) {
        return new TransactionDto(transaction.getId(), transaction.getCreditAccount(),
                transaction.getDebitAccount(), transaction.getAmount(), null, 0, null);
    }

    @Override
    public Transaction toEntity(TransactionDto transaction) {
        return new Transaction(transaction.getId(), transaction.getCreditAccount(),
                transaction.getDebitAccount(), transaction.getAmount(),
                transaction.getDescription(), transaction.getType(),
                new Timestamp(System.currentTimeMillis()));
    }
}
