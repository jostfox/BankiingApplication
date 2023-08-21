package org.example.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.example.entity.Client;
import org.example.enums.Status;

import javax.persistence.Enumerated;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ManagerDto {

    private long id;
    private String firstName;
    private String lastName;
    @Enumerated
    private Status status;
    private String description;
    private LocalDateTime createdAt;
    private List<ProductDto> products = new ArrayList<>();

    public ManagerDto() {
    }

    public ManagerDto(long id, String firstName, String lastName, Status status,
                      String description, LocalDateTime createdAt, List<ProductDto> products) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.status = status;
        this.description = description;
        this.createdAt = createdAt;
        this.products = products;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    //    public List<ClientDto> getClients() {
//        return clients;
//    }

//    public void setClients(List<ClientDto> clients) {
//        this.clients = clients;
//    }

    public List<ProductDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDto> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "ManagerDto{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", status=" + status +
                ", description='" + description + '\'' +
                ", createdAt=" + createdAt +
                ", products=" + products +
                '}';
    }
}
