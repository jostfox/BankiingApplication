package org.example.converter;

import org.example.dto.AccountDto;
import org.example.dto.ClientDto;
import org.example.dto.ManagerDto;
import org.example.entity.Client;
import org.example.enums.Roles;
import org.example.enums.Status;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.stream.Collectors;

@Component
public class ClientConverter implements Converter<Client, ClientDto> {

    @Override
    public ClientDto toDto(Client client) {
        return new ClientDto(client.getId(), client.getFirstName(), client.getLastName(),
                client.getLogin(), null, null, null, null, null, null, null, null,
                client.getManager() == null ? null : new ManagerDto(client.getManager().getId(),
                        client.getManager().getFirstName(), client.getManager().getLastName(),
                        null, null, null, null, null, null, null), client.getAccounts() == null ?
                null :
                client.getAccounts().stream().map(account -> new AccountDto(account.getIban(),
                        account.getStatus(), null, null)).collect(Collectors.toList()), null);
    }


    @Override
    public Client toEntity(ClientDto client) {
        return new Client(client.getId(), client.getFirstName(), client.getLastName(),
                client.getLogin(), client.getPassword(), client.getTaxCode(), client.getAddress()
                , client.getPhone(), client.getEmail(), Status.ACTIVE,
                new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis()), null, null, Roles.USER);
    }
}
