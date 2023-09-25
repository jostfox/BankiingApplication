/*package org.example.service;

import org.example.entity.Client;
import org.example.enums.Roles;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomClientDetailService implements UserDetailsService {

    @Autowired
    private ClientServiceImpl clientService;


    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Client client = clientService.getByLogin(login);
        if (client == null) {
            throw new UsernameNotFoundException("User with login " + login + " " + "not found");
        }

        return User.builder()
                .username(client.getLogin())
                .password(client.getPassword())
                .authorities(Roles.USER.getAuthorities()).build();
    }
}*/


