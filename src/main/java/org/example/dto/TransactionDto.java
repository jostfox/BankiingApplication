package org.example.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.example.enums.TransactionType;

import java.math.BigDecimal;
import java.sql.Timestamp;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionDto {

    private Long id;
    private String creditAccount;
    private String debitAccount;
    private BigDecimal amount;
    private String description;
    private TransactionType type;
    private Timestamp createdAt;

    public TransactionDto() {
        //
    }

    public TransactionDto(Long id, String creditAccount, String debitAccount,
                          BigDecimal amount, String description, TransactionType type, Timestamp createdAt) {
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

    public String getCreditAccount() {
        return creditAccount;
    }

    public void setCreditAccount(String creditAccount) {
        this.creditAccount = creditAccount;
    }

    public String getDebitAccount() {
        return debitAccount;
    }

    public void setDebitAccount(String debitAccount) {
        this.debitAccount = debitAccount;
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

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "TransactionDto{" +
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
