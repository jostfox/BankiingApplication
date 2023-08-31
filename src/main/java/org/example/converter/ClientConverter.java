package org.example.converter;

import org.example.dto.AccountDto;
import org.example.dto.ClientDto;
import org.example.dto.ManagerDto;
import org.example.entity.Client;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ClientConverter implements Converter<Client, ClientDto> {
    @Override
    public ClientDto toDto(Client client) {
        return new ClientDto(client.getId(), client.getFirstName(),
                client.getLastName(), null, null, null, null, null,
                null, null,
                client.getManager()==null ? null :
                new ManagerDto(client.getManager().getId(),
                        client.getManager().getFirstName(),
                        client.getManager().getLastName(), null, client.getManager().
                        getDescription(), null, null, null),
                client.getAccounts().stream()
                        .map(account -> new AccountDto(account.getIban(), account.getStatus()))
                        .collect(Collectors.toList()));
    }

    @Override
    public Client toEntity(ClientDto client) {
        return new Client(client.getFirstName(), client.getLastName(),
                client.getTaxCode(), client.getAddress(), client.getPhone(),
                client.getEmail(), client.getStatus(), client.getCreatedAt(),
                client.getUpdatedAt(), null);
    }
}
