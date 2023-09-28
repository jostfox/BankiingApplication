package org.example.security;

import org.example.entity.Client;
import org.example.exceptions.ItemNotFoundException;
import org.example.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("clientDetailsServiceImpl")
public class ClientDetailsServiceImpl implements UserDetailsService {

    private ClientRepository clientRepository;

    @Autowired
    public ClientDetailsServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public ClientDetailsServiceImpl() {
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Client client = clientRepository.findByLogin(login).orElseThrow(() ->
                new ItemNotFoundException(String.format("Client with login +" +
                                "\"%s\" not found",
                        login)));
        return SecurityClient.fromClient(client);
    }
}
