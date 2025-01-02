package org.example.service;


import org.example.entity.Client;

import org.example.exceptions.ItemNotFoundException;
import org.example.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


import java.util.List;

@Service("clientService")
public class ClientServiceImpl implements ClientService {

    private ClientRepository clientRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public ClientServiceImpl() {
    }

    //Admin`s method
    @Override
    public List<Client> getAll() {
        return clientRepository.findAll();
    }

    //Admin`s method
    @Override
    public Client getById(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException(String.format("Client with id %d not found", id)));
    }

    //Admin`s method
    @Override
    public Client getByName(String firstName, String lastName) {
        return getAll().stream()
                .filter(client -> client.getFirstName().equalsIgnoreCase(firstName) & client.getLastName().equalsIgnoreCase(lastName))
                .findFirst().orElseThrow(() ->
                        new ItemNotFoundException(String.format("Client " + "%s %s not found", firstName, lastName)));
    }

    //Admin`s method
    @Override
    public Client getByLogin(String login) {
        return clientRepository.findByLogin(login).orElseThrow(() ->
                new ItemNotFoundException(String.format("Client "
                        + "with login \"%s\" not found", login)));
    }

    @Override
    public Client save (Client client) {
        return clientRepository.save(client);
    }

    @Override
    public void remove() {
        clientRepository.delete(getCurrent());
    }

    @Override
    public Client getCurrent() {
        return getByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
    }
}
