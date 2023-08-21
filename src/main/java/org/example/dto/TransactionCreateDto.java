package org.example.dto;

import org.example.entity.Account;

import java.time.LocalDateTime;

public class TransactionCreateDto {

    private Long id;
    private Account creditAccount;
    private Account debitAccount;
    private double amount;
    private String description;
    private int type;
    private LocalDateTime createdAt;

    public TransactionCreateDto() {
    }

    public TransactionCreateDto(Long id, Account creditAccount, Account debitAccount,
                                double amount, String description, int type,
                                LocalDateTime createdAt) {
        this.id = id;
        this.creditAccount = creditAccount;
        this.debitAccount = debitAccount;
        this.amount = amount;
        this.description = description;
        this.type = type;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Account getCreditAccount() {
        return creditAccount;
    }

    public void setCreditAccount(Account creditAccount) {
        this.creditAccount = creditAccount;
    }

    public Account getDebitAccount() {
        return debitAccount;
    }

    public void setDebitAccount(Account debitAccount) {
        this.debitAccount = debitAccount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "TransactionCreateDto{" +
                "id=" + id +
                ", creditAccount=" + creditAccount +
                ", debitAccount=" + debitAccount +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", type=" + type +
                ", createdAt=" + createdAt +
                '}';
    }
}