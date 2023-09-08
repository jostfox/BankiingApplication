package org.example.entity;

import org.example.enums.TransactionType;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "debit_account", referencedColumnName = "iban")
    private Account debitAccount;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "credit_account", referencedColumnName = "iban")
    private Account creditAccount;

    @Enumerated(value = EnumType.STRING)
    private TransactionType type;

    private BigDecimal amount;

    private String description;

    private Timestamp createdAt;

    public Transaction() {
        //
    }

    public Transaction(Account debitAccount, Account creditAccount, TransactionType type, BigDecimal amount,
                       String description, Timestamp createdAt) {
        this.debitAccount = debitAccount;
        this.creditAccount = creditAccount;
        this.type = type;
        this.amount = amount;
        this.description = description;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Account getDebitAccount() {
        return debitAccount;
    }

    public void setDebitAccount(Account debitAccountId) {
        this.debitAccount = debitAccountId;
    }

    public Account getCreditAccount() {
        return creditAccount;
    }

    public void setCreditAccount(Account creditAccountId) {
        this.creditAccount = creditAccountId;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", debitAccountId=" + debitAccount +
                ", creditAccountId=" + creditAccount +
                ", type=" + type +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
