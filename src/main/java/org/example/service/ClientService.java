package org.example.service;

import org.example.entity.Client;

import java.util.List;

public interface ClientService {

    List<Client> getAll();

    Client getById(Long id);

    Client getByName(String firstName, String lastName);

    Client getByLogin(String login);

    Client save(Client client);

    void remove();

    Client getCurrent();
}
