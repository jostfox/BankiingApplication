package org.example.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.example.enums.Roles;
import org.example.enums.Status;

import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClientDto {

    private Long id;

    private String firstName;

    private String lastName;

    private String login;

    private String password;

    private String taxCode;

    private String address;

    private String phone;

    private String email;

    @Enumerated
    private Status status;

    private Timestamp createdAt;

    private Timestamp updatedAt;

    private ManagerDto manager;

    private List<AccountDto> accounts = new ArrayList<>();

    private Roles role;

    public ClientDto() {
    }

    public ClientDto(Long id, String firstName, String lastName, String login, String password,
                     String taxCode, String address, String phone, String email, Status status,
                     Timestamp createdAt, Timestamp updatedAt, ManagerDto manager,
                     List<AccountDto> accounts, Roles role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
        this.taxCode = taxCode;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.manager = manager;
        this.accounts = accounts;
        this.role = role;
    }

    public ClientDto(Long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
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

    public ManagerDto getManager() {
        return manager;
    }

    public void setManager(ManagerDto manager) {
        this.manager = manager;
    }

    public List<AccountDto> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<AccountDto> accounts) {
        this.accounts = accounts;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "ClientDto{" + "id=" + id + ", firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", login='" + login + '\'' + ", password='" + password + '\'' + ", taxCode='" + taxCode + '\'' + ", address='" + address + '\'' + ", phone='" + phone + '\'' + ", email='" + email + '\'' + ", status=" + status + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", manager=" + manager + ", accounts=" + accounts + '}';
    }
}
