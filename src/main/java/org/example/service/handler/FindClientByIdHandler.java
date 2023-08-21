package org.example.service.handler;

import org.example.entity.Client;
import org.example.exceptions.ItemNotFoundException;
import org.example.repositories.ClientRepository;
import org.springframework.stereotype.Component;

@Component
public class FindClientByIdHandler implements FindById<Client, ClientRepository> {

    @Override
    public Client findByIdHandledWithException(Long id, ClientRepository clientRepository) {
        Client client = clientRepository.findById(id).orElse(null);
        if (client == null) {
            throw new ItemNotFoundException(String.format("Client with id %d not found", id));
        }
        return client;
    }
}

