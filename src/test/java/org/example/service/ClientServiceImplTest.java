package org.example.service;

import org.example.entity.Client;
import org.example.repositories.ClientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ClientServiceImplTest {

    @InjectMocks
    private ClientServiceImpl clientService;

    @Mock
    private  ClientRepository clientRepository;

    @Test
    void getById() {
        Client client = new Client();
        client.setId(3L);
        Mockito
                .when(clientRepository.findById(client.getId()))
                .thenReturn(Optional.of(client));

        Client anyClient = clientService.getById(3L);
        assertEquals(3L, anyClient.getId());
    }

    @Test
    void getByLogin() {
        Client client = new Client();
        client.setLogin("testLogin");
        Mockito
                .when(clientRepository.findByLogin(client.getLogin()))
                .thenReturn(Optional.of(client));

        Client admin = clientService.getByLogin("testLogin");
        assertEquals("testLogin", admin.getLogin());
    }
}