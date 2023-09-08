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
        List<Client> clients = clientRepository.findAll();
        for (Client client : clients) {
            if (client.getFirstName().equals(firstName) & client.getLastName().equals(lastName)) {
                return client;
            }
        }
        throw new ItemNotFoundException(String.format("Client %s %s not found", firstName,
                lastName));
    }

    @Override
    public Client getByLogin(String login) {
        return clientRepository.findByLogin(login);
    }

    @Override
    public Client add(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public void remove(Long id) {
        Client client = getById(id);
        clientRepository.delete(client);
    }

    @Override
    public Client update(Long id) {
        Client client = getById(id);
        client.setAddress(client.getAddress());
        client.setEmail(client.getEmail());
        client.setPhone(client.getPhone());
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
