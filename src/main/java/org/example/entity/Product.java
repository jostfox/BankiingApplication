package org.example.entity;

import javax.persistence.*;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private int status;
    private int currencyCode;
    private double interestRate;
    private int prodLimit;
//    private LocalDateTime createdAt;
//    private LocalDateTime updatedAt;

    public Product() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(int currencyCode) {
        this.currencyCode = currencyCode;
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

    public void setProdLimit(int limit) {
        this.prodLimit = limit;
    }

//    public LocalDateTime getCreatedAt() {
//        return createdAt;
//    }

//    public void setCreatedAt(LocalDateTime createdAt) {
//        this.createdAt = createdAt;
//    }

//    public LocalDateTime getUpdatedAt() {
//        return updatedAt;
//    }

//    public void setUpdatedAt(LocalDateTime updatedAt) {
//        this.updatedAt = updatedAt;
//    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                /*", managerId=" + managerId +*/
                ", name='" + name + '\'' +
                ", status=" + status +
                ", currencyCode=" + currencyCode +
                ", interestRate=" + interestRate +
                /*", limit=" + limit +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +*/
                '}';
    }
}
