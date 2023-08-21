package org.example.dto;

import org.example.entity.Manager;
import org.example.enums.Currency;
import org.example.enums.Status;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

public class ProductCreateDto {

    private Long id;
    private String name;
    private Manager manager;
    @Enumerated
    private Status status;
    @Enumerated(EnumType.STRING)
    private Currency currency;
    private double interestRate;
    private int prodLimit;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ProductCreateDto() {
    }

    public ProductCreateDto(Long id, String name, Manager manager, Status status,
                            Currency currency, double interestRate, int prodLimit,
                            LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.manager = manager;
        this.status = status;
        this.currency = currency;
        this.interestRate = interestRate;
        this.prodLimit = prodLimit;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public int getProdLimit() {
        return prodLimit;
    }

    public void setProdLimit(int prodLimit) {
        this.prodLimit = prodLimit;
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
        return "ProductCreateDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", manager=" + manager +
                ", status=" + status +
                ", currency=" + currency +
                ", interestRate=" + interestRate +
                ", prodLimit=" + prodLimit +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
