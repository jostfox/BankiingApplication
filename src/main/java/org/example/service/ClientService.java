package org.example.service;

import org.example.entity.Account;
import org.example.entity.Client;
import org.example.enums.Status;

import java.util.List;

public interface ClientService {

    public List<Client> getAll();

    public Client getById(Long id);

    public Client getByName(String firstName, String lastName);

    public Client getByLogin(String login);

    public Client save (Client client);

    public void remove();

    public Client getCurrent();
}
