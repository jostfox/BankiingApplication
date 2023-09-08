package org.example.converter;

import org.example.dto.ClientDto;
import org.example.dto.ManagerDto;
import org.example.dto.ProductDto;
import org.example.entity.Manager;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.stream.Collectors;

@Component
public class ManagerConverter implements Converter<Manager, ManagerDto> {
    @Override
    public ManagerDto toDto(Manager manager) {
        return new ManagerDto(manager.getId(), manager.getFirstName(), manager.getLastName(),
                manager.getLogin(), null, manager.getStatus(), manager.getDescription(), null,
                manager.getClients() == null ? null :
                        manager.getClients().stream().map(client -> new ClientDto(client.getId(),
                                client.getFirstName(), client.getLastName()))
                                .collect(Collectors.toList()),
                manager.getProducts() == null ? null : manager.getProducts().stream().
                        map(product -> new ProductDto(product.getId(), product.getName(), null))
                        .collect(Collectors.toList()));
    }

    @Override
    public Manager toEntity(ManagerDto manager) {
        return new Manager(manager.getId(), manager.getFirstName(), manager.getLastName(),
                manager.getLogin(), manager.getPassword(), manager.getStatus(),
                manager.getDescription(), new Timestamp(System.currentTimeMillis()),
                null, null);
    }
}
