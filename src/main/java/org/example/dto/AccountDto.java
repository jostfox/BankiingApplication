package org.example.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.example.entity.Client;
import org.example.enums.Currency;
import org.example.enums.Status;

import java.sql.Timestamp;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountDto {

    private String iban;

    private Client client;

    private Status status;

    private String name;

    private int type;

    private double balance;

    private Currency currency;

    private Timestamp createdAt;

    private Timestamp updatedAt;

    public AccountDto() {
    }

    public AccountDto(String iban, Status status, double balance) {
        this.iban = iban;
        this.status = status;
        this.balance = balance;
    }

    public AccountDto(String iban, Client client, Status status, String name,
                      int type, double balance, Currency currency,
                      Timestamp createdAt, Timestamp updatedAt) {
        this.iban = iban;
        this.client = client;
        this.status = status;
        this.name = name;
        this.type = type;
        this.balance = balance;
        this.currency = currency;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "AccountDto{" +
                "iban='" + iban + '\'' +
                ", client=" + client +
                ", status=" + status +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", balance=" + balance +
                ", currency=" + currency +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
