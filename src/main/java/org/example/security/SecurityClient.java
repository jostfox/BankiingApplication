package org.example.security;


import org.example.entity.Client;
import org.example.enums.Status;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public class SecurityClient extends SecurityEntity {

    public SecurityClient(String login, String password,
                          List<SimpleGrantedAuthority> authority,
                          boolean isActive) {
        super(login, password, authority, isActive);
    }

    public static UserDetails fromClient(Client client){
        return new User(client.getLogin(), client.getPassword(),
                client.getStatus().equals(Status.ACTIVE),
                client.getStatus().equals(Status.ACTIVE),
                client.getStatus().equals(Status.ACTIVE),
                client.getStatus().equals(Status.ACTIVE),
                client.getRole().getAuthorities());
    }
}
