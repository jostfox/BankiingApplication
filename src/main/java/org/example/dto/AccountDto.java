package org.example.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountDto {

    private long id;
    private String iban;

    public AccountDto() {
    }

    public AccountDto(long id, String iban) {
        this.id = id;
        this.iban = iban;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                "iban='" + iban + '\'' +
                '}';
    }
}
