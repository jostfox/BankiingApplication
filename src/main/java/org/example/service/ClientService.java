package org.example.service;

import org.example.entity.Client;

import java.util.List;

public interface ClientService {

    public List<Client> getAll();

    public Client newClient(Client client);
}
