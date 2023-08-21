package org.example.converter;

import org.example.dto.ManagerCreateDto;
import org.example.dto.ManagerDto;
import org.example.dto.ProductDto;
import org.example.entity.Manager;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ManagerConverter implements Converter<Manager, ManagerDto, ManagerCreateDto> {
    @Override
    public ManagerDto toDto(Manager manager) {
        return new ManagerDto(manager.getId(), manager.getFirstName(),
                manager.getLastName(), null, manager.getDescription(), manager.getCreatedAt(),
                manager.getProducts().stream().
                        map(product -> new ProductDto(product.getId(),
                                product.getName(), null))
                        .collect(Collectors.toList()));
    }

    @Override
    public Manager toEntity(ManagerCreateDto manager) {
        return new Manager(manager.getId(), manager.getFirstName(),
                manager.getLastName(), manager.getStatus(), manager.getDescription(),
                manager.getCreatedAt());
    }
}
