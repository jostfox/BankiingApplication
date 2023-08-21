package org.example.dto;

import org.example.enums.Status;

import javax.persistence.Enumerated;
import java.time.LocalDateTime;

public class ClientCreateDto {

    private long id;
    private String firstName;
    private String lastName;
    private String taxCode;
    private String address;
    private String phone;
    private String email;
    @Enumerated
    private Status status;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private ManagerDto manager;

    public ClientCreateDto() {
    }

    public ClientCreateDto(long id, String firstName, String lastName, String taxCode,
                           String address, String phone, String email, Status status,
                           LocalDateTime createdAt, LocalDateTime updatedAt, ManagerDto manager) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.taxCode = taxCode;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.manager = manager;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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

    public ManagerDto getManager() {
        return manager;
    }

    public void setManager(ManagerDto manager) {
        this.manager = manager;
    }

    @Override
    public String toString() {
        return "ClientCreateDto{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", taxCode='" + taxCode + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", status=" + status +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", manager=" + manager +
                '}';
    }
}


