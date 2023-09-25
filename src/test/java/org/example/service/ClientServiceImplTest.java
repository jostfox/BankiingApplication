package org.example.service;

import org.example.entity.Client;
import org.example.repositories.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ClientServiceImplTest.ClientServiceTestConfig.class})
class ClientServiceImplTest {

    @TestConfiguration
    static class ClientServiceTestConfig {
        @Bean
        public ClientService clientService(){
            return new ClientServiceImpl();
        }
    }

    @Autowired
    private ClientService clientService;

    @MockBean
    private ClientRepository clientRepository;

    @Mock
    private List<String> list;

    @BeforeEach
    public void init(){
        Client client = new Client();
        client.setLogin("testlogin");
        Mockito.when(clientService.getByLogin(client.getLogin())).thenReturn(client);
    }

    @Test
    void getAll() {
    }

    @Test
    void getById() {
    }

    @Test
    void getByName() {
    }

    @Test
    void getByLogin() {
    }

    @Test
    void save() {
    }

    @Test
    void remove() {
    }

    @Test
    void getCurrent() {
    }
}