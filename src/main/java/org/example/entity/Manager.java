package org.example.entity;

import org.example.enums.Status;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "managers")
public class Manager {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String login;

    private String password;

    @Enumerated(EnumType.STRING)
    private Status status;

    private String description;

    private Timestamp createdAt;

    @OneToMany(mappedBy = "manager")
    List<Client> clients = new ArrayList<>();

    @OneToMany(mappedBy = "manager")
    List<Product> products = new ArrayList<>();

    public Manager() {
    }

    public Manager(Long id, String firstName, String lastName, String login, String password,
                   Status status, String description, Timestamp createdAt, List<Client> clients,
                   List<Product> products) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
        this.status = status;
        this.description = description;
        this.createdAt = createdAt;
        this.clients = clients;
        this.products = products;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    @Override
    public String toString() {
        return "Manager{" + "id=" + id + ", firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", login='" + login + '\'' + ", status=" + status + '\'' + ", description='" + description + '\'' + ", createdAt=" + createdAt + '\'' + ", clients=" + clients + '\'' + ", products=" + products + '}';
    }
}
