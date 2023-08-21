package org.example.service;

import org.example.entity.Account;
import org.example.entity.Client;
import org.example.enums.Status;
import org.example.exceptions.ItemNotFoundException;
import org.example.repositories.AccountRepository;
import org.example.repositories.ClientRepository;
import org.example.service.handler.FindById;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    private FindById<Client, ClientRepository> findClientById;

    @Autowired
    private FindById<Account, AccountRepository> findAccountById;

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
            if (client.getFirstName().equals(firstName) &
                    client.getLastName().equals(lastName)) {
                return client;
            }
        }
        throw new ItemNotFoundException(String.format("Client %s %s not found",
                firstName, lastName));
    }

    @Override
    public Client add(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public void remove(Long id) {
        clientRepository.delete(findClientById.findByIdHandledWithException(id, clientRepository));
    }

    @Override
    public Client update(Long id) {
        Client client = clientRepository.getReferenceById(id);
        client.setAddress(client.getAddress());
        client.setEmail(client.getEmail());
        client.setPhone(client.getPhone());
        ;
        return clientRepository.save(client);
    }

    @Override
    public Client changeStatus(Long id, Status status) {
        Client client = findClientById.findByIdHandledWithException(id, clientRepository);
        client.setStatus(status);
        return clientRepository.save(client);
    }

    @Override
    public double checkBalance(Long clientId, Long accountId) {
        Client client = findClientById.findByIdHandledWithException(clientId, clientRepository);
        List<Account> accounts = client.getAccounts();
        for (Account account : accounts) {
            if (Objects.equals(account.getId(), accountId)) {
                return account.getBalance();
            }
        }
        throw new ItemNotFoundException(String.format("Account with id %d not found", accountId));
    }

    @Override
    public void closeAccount(Long clientId, Long accountId) {
        Client client = findClientById.findByIdHandledWithException(clientId, clientRepository);
        List<Account> accounts = client.getAccounts();
        for (Account account : accounts) {
            if (Objects.equals(account.getId(), accountId)) {
                accountRepository.delete(account);
                return;
            }
        }
        throw new ItemNotFoundException(String.format("Account with id %d not found", accountId));
    }

    @Override
    public void topUpAccount(Long clientId, Long accountId, double amount) {
        Client client = findClientById.findByIdHandledWithException(clientId, clientRepository);
        List<Account> accounts = client.getAccounts();
        for (Account account : accounts) {
            if (Objects.equals(account.getId(), accountId)) {
                account.setBalance(account.getBalance() + amount);
                accountRepository.save(account);
                return;
            }
        }
        throw new ItemNotFoundException(String.format("Account with id %d not found", accountId));
    }
}
