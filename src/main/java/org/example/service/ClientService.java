package org.example.service;

import org.example.entity.Account;
import org.example.entity.Client;
import org.example.enums.Status;

import java.util.List;

public interface ClientService {

    public List<Client> getAll();

    public Client getById(Long id);

    public Client getByName(String firstName, String lastName);

    public Client add(Client client);

    public void remove(Long id);
    public Client update(Long id);

    public Client changeStatus(Long id, Status status);

    public double checkBalance(Long clientId, Long accountId);

    public void closeAccount(Long clientId, Long accountId);

    public void topUpAccount(Long clientId, Long accountId, double amount);



}
