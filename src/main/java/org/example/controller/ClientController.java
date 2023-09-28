package org.example.controller;

import org.example.converter.Converter;
import org.example.dto.ClientDto;
import org.example.entity.Client;
import org.example.service.ClientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final PasswordEncoder passwordEncoder;
    private final ClientServiceImpl clientService;
    private final Converter<Client, ClientDto> clientConverter;

    @Autowired
    public ClientController(PasswordEncoder passwordEncoder, ClientServiceImpl clientService,
                            Converter<Client, ClientDto> clientConverter) {
        this.passwordEncoder = passwordEncoder;
        this.clientService = clientService;
        this.clientConverter = clientConverter;
    }

    //Admin`s method
    @GetMapping
    @PreAuthorize("hasAuthority('permission:admin')")
    List<ClientDto> getAll() {
        return clientService.getAll().stream().map(clientConverter::toDto).collect(Collectors.toList());
    }

    //Admin`s method
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('permission:admin')")
    ClientDto getById(@PathVariable("id") Long id) {
        return clientConverter.toDto(clientService.getById(id));
    }

    //Admin`s method
    @GetMapping("/{firstName}/{lastName}")
    @PreAuthorize("hasAuthority('permission:admin')")
    ClientDto getByName(@PathVariable("firstName") String firstName,
                        @PathVariable("lastName") String lastName) {
        return clientConverter.toDto(clientService.getByName(firstName, lastName));
    }

    //Admin`s method
    @GetMapping("/login/{login}")
    @PreAuthorize("hasAuthority('permission:admin')")
    ClientDto getByLogin(@PathVariable("login") String login) {
        return clientConverter.toDto(clientService.getByLogin(login));
    }


    @PostMapping
    ResponseEntity<ClientDto> add(@RequestBody ClientDto client) {
        client.setPassword(passwordEncoder.encode(client.getPassword()));
        return ResponseEntity.ok(clientConverter.toDto(clientService.save(clientConverter.toEntity(client))));
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('permission:user')")
    public void remove() {
        clientService.remove();
    }

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

    @PutMapping("/status")
    @PreAuthorize("hasAuthority('permission:admin')")
    public ResponseEntity<ClientDto> changeStatus(@RequestBody ClientDto clientDto) {
        Client client = clientService.getCurrent();
        client.setStatus(clientDto.getStatus());
        clientService.save(client);
        return ResponseEntity.ok(clientConverter.toDto(client));
    }
}
