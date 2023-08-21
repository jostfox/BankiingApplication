package org.example.dto;

import org.example.entity.Client;
import org.example.enums.Currency;
import org.example.enums.Status;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

public class AccountCreateDto {

    private Long id;
    private Client client;
    private String iban;
    private String name;
    private int type;
    @Enumerated
    private Status status;
    private double balance;
    @Enumerated(EnumType.STRING)
    private Currency currency;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public AccountCreateDto() {
    }

    public AccountCreateDto(Long id, Client client, String iban, String name,
                            int type, Status status, double balance, Currency currency,
                            LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.client = client;
        this.iban = iban;
        this.name = name;
        this.type = type;
        this.status = status;
        this.balance = balance;
        this.currency = currency;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "AccountCreateDto{" +
                "id=" + id +
                ", client=" + client +
                ", iban='" + iban + '\'' +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", status=" + status +
                ", balance=" + balance +
                ", currency=" + currency +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
