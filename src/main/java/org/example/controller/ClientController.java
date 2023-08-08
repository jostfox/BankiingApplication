package org.example.controller;

import org.example.entity.Client;
import org.example.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("client")
public class ClientController {

    @Autowired
    ClientService clientService;

    @GetMapping
    List<Client> getAll() {
        return clientService.getAll();
    }

    @PostMapping
    ResponseEntity<Client> newClient(@RequestBody Client client) {
        return ResponseEntity.ok(clientService.newClient(client));
    }
}
