package org.example.service;

import org.example.entity.Account;
import org.example.entity.Client;
import org.example.enums.Status;
import org.example.exceptions.ItemNotFoundException;
import org.example.exceptions.NotEmptyBalanceException;
import org.example.repositories.AccountRepository;
import org.example.repositories.ClientRepository;
import org.example.service.handler.FindById;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    private FindById<Client, ClientRepository> findClientById;

    @Override
    public List<Client> getAll() {
        return clientRepository.findAll();
    }

    @Override
    public Client getById(Long id) {
        return findClientById.findByIdHandledWithException(id, clientRepository);
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
        Client client = findClientById.findByIdHandledWithException(id, clientRepository);
        clientRepository.delete(client);
    }

    @Override
    public Client update(Long id) {
        Client client = clientRepository.getReferenceById(id);
        client.setAddress(client.getAddress());
        client.setEmail(client.getEmail());
        client.setPhone(client.getPhone());
        client.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        return clientRepository.save(client);
    }

    @Override
    public Client changeStatus(Long id, Status status) {
        Client client = findClientById.findByIdHandledWithException(id, clientRepository);
        client.setStatus(status);
        return clientRepository.save(client);
    }

    @Override
    public Client getCurrent() {
        return getByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
    }
}
