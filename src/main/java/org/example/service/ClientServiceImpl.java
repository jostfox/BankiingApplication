package org.example.service;


import org.example.entity.Client;
import org.example.enums.Status;
import org.example.exceptions.ItemNotFoundException;
import org.example.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public List<Client> getAll() {
        return clientRepository.findAll();
    }

    @Override
    public Client getById(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException(String.format("Client with id %d not found", id)));
    }

    @Override
    public Client getByName(String firstName, String lastName) {
        return getAll().stream()
                .filter(client -> client.getFirstName().equals(firstName) & client.getLastName().equals(lastName))
                .findFirst().orElseThrow(() ->
                        new ItemNotFoundException(String.format("Client " + "%s %s not found", firstName, lastName)));
    }

    @Override
    public Client getByLogin(String login) {
        return getAll().stream()
                .filter(client -> client.getLogin().equals(login)).findFirst()
                .orElseThrow(() ->
                        new ItemNotFoundException(String.format("Client " + "with login \"%s\" not found", login)));
    }

    @Override
    public Client add(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public void remove(Long id) {
        clientRepository.delete(getById(id));
    }

    @Override
    public Client update(Long id) {
        Client client = getById(id);
        if (client.getAddress() != null) client.setAddress(client.getAddress());
        if (client.getEmail() != null) client.setEmail(client.getEmail());
        if (client.getPhone() != null) client.setPhone(client.getPhone());
        client.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        return clientRepository.save(client);
    }

    @Override
    public Client changeStatus(Long id, Status status) {
        Client client = getById(id);
        client.setStatus(status);
        return clientRepository.save(client);
    }

//    @Override
//    public Client getCurrent() {
//        return getByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
//    }
}
