package org.example.dto;

import org.example.entity.Account;

public class TransactionDto {

    private Long id;
    private Account creditAccount;
    private Account debitAccount;
    private double amount;
    private String description;

    public TransactionDto() {
    }

    public TransactionDto(Long id, Account creditAccount, Account debitAccount, double amount, String description) {
        this.id = id;
        this.creditAccount = creditAccount;
        this.debitAccount = debitAccount;
        this.amount = amount;
        this.description = description;
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

    @Override
    public String toString() {
        return "TransactionDto{" +
                "id=" + id +
                ", creditAccount=" + creditAccount +
                ", debitAccount=" + debitAccount +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                '}';
    }
}
