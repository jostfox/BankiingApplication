package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.converter.Converter;
import org.example.dto.ClientDto;
import org.example.entity.Client;
import org.example.service.ClientService;
import org.example.service.ClientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Client controller",
        description = "Provides CRUD operations with the clients")
@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final PasswordEncoder passwordEncoder;
    private final ClientService clientService;
    private final Converter<Client, ClientDto> clientConverter;

    @Autowired
    public ClientController(PasswordEncoder passwordEncoder,
                            @Qualifier("clientService") ClientService clientService,
                            Converter<Client, ClientDto> clientConverter) {
        this.passwordEncoder = passwordEncoder;
        this.clientService = clientService;
        this.clientConverter = clientConverter;
    }

    @Operation(summary = "List of clients",
    description = "Get a list of all clients")
    @SecurityRequirement(name = "basicauth")
    @GetMapping
    @PreAuthorize("hasAuthority('permission:admin')")
    List<ClientDto> getAll() {
        return clientService.getAll().stream().map(clientConverter::toDto).collect(Collectors.toList());
    }

    @Operation(summary = "Get by ID",
            description = "Get a client by ID")
    @SecurityRequirement(name = "basicauth")
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('permission:admin')")
    ClientDto getById(@PathVariable("id")
                      @Parameter(description = "Client`s ID") Long id) {
        return clientConverter.toDto(clientService.getById(id));
    }

    @Operation(summary = "Get by name",
            description = "Get a client by his name and lastname")
    @SecurityRequirement(name = "basicauth")
    @GetMapping("/{firstName}/{lastName}")
    @PreAuthorize("hasAuthority('permission:admin')")
    ClientDto getByName(@PathVariable("firstName") String firstName,
                        @PathVariable("lastName") String lastName) {
        return clientConverter.toDto(clientService.getByName(firstName, lastName));
    }

    @Operation(summary = "Get by login",
            description = "Get a client by login")
    @SecurityRequirement(name = "basicauth")
    @GetMapping("/login/{login}")
    @PreAuthorize("hasAuthority('permission:admin')")
    ClientDto getByLogin(@PathVariable("login") String login) {
        return clientConverter.toDto(clientService.getByLogin(login));
    }

    @Operation(summary = "Add a new client",
            description = "Adds a new client into the database")
    @SecurityRequirement(name = "basicauth")
    @PostMapping
    ResponseEntity<ClientDto> add(@RequestBody ClientDto client) {
        client.setPassword(passwordEncoder.encode(client.getPassword()));
        return ResponseEntity.ok(clientConverter.toDto(clientService.save(clientConverter.toEntity(client))));
    }

    @Operation(summary = "Remove client",
            description = "Removes client from the database")
    @SecurityRequirement(name = "basicauth")
    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('permission:user')")
    public void remove() {
        clientService.remove();
    }

    @Operation(summary = "Update client",
            description = "Updates any client`s data in the database")
    @SecurityRequirement(name = "basicauth")
    @PutMapping("/update")
    @PreAuthorize("hasAuthority('permission:user')")
    public ResponseEntity<ClientDto> update(@RequestBody ClientDto clientDto) {
        Client client = clientService.getCurrent();
        if (clientDto.getPhone() != null) client.setPhone(clientDto.getPhone());
        if (clientDto.getEmail() != null) client.setEmail(clientDto.getEmail());
        if (clientDto.getAddress() != null) client.setAddress(clientDto.getAddress());
        clientService.save(client);
        return ResponseEntity.ok(clientConverter.toDto(client));
    }

    @Operation(summary = "Status update",
            description = "Updates the client`s status")
    @SecurityRequirement(name = "basicauth")
    @PutMapping("/status")
    @PreAuthorize("hasAuthority('permission:admin')")
    public ResponseEntity<ClientDto> changeStatus(@RequestBody ClientDto clientDto) {
        Client client = clientService.getCurrent();
        client.setStatus(clientDto.getStatus());
        clientService.save(client);
        return ResponseEntity.ok(clientConverter.toDto(client));
    }
}
