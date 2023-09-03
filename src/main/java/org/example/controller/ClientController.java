package org.example.controller;

import org.example.converter.Converter;
import org.example.dto.ClientDto;
import org.example.entity.Client;
import org.example.enums.Status;
import org.example.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/profile")
public class ClientController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ClientService clientService;

    @Autowired
    private Converter<Client, ClientDto> clientConverter;

//    @PostMapping
//    public Client create (Client client){
//        client.setPassword(passwordEncoder.encode(client.getPassword()));
//        return clientService.add(client);
//    }

    @GetMapping
    List<ClientDto> getAll() {
        return clientService.getAll().stream()
                .map(clientConverter::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    ClientDto getById(@PathVariable("id") Long id){
        return clientConverter.toDto(clientService.getById(id));
    }

    @GetMapping("/{firstName}/{lastName}")
    ClientDto getByName(@PathVariable("firstName") String firstName,
                        @PathVariable("lastName") String lastName){
        return clientConverter.toDto(clientService.getByName(firstName, lastName));
    }


    @PostMapping
    ResponseEntity<ClientDto> add(@RequestBody ClientDto client) {
        return ResponseEntity.ok(clientConverter.toDto(clientService.add(clientConverter.toEntity(client))));
    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable("id") Long id){
        clientService.remove(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> update(@PathVariable("id") Long id,
                                         @RequestBody Client client){
        return ResponseEntity.ok(clientService.update(id));
    }

    @PutMapping("/{id}/status/{status}")
    public ClientDto changeStatus(@PathVariable("id") Long id,
                                  @PathVariable("status")Status status){
        return clientConverter.toDto(clientService.changeStatus(id, status));
    }

//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(ConstraintViolationException.class)
//    public Map<String, String> exceptionHandler(ConstraintViolationException exception) {
//        Map<String, String> map = new HashMap<>();
//        exception.getConstraintViolations().forEach(error -> {
//            map.put(error.getPropertyPath().toString(), error.getMessage());
//        });
//        return map;
//    }
}
