package org.example.controller;

import org.example.converter.Converter;
import org.example.dto.ManagerDto;
import org.example.entity.Manager;
import org.example.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/managers")
public class ManagerController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ManagerService managerService;

    @Autowired
    private Converter<Manager, ManagerDto> managerConverter;

    @GetMapping
    List<ManagerDto> getAll() {
        return managerService.getAll().stream()
                .map(manager -> managerConverter.toDto(manager))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    ManagerDto getById(@PathVariable("id") Long id){
        return managerConverter.toDto(managerService.getById(id));
    }

    @PostMapping
    ResponseEntity<ManagerDto> add (@RequestBody ManagerDto manager) {
        manager.setPassword(passwordEncoder.encode(manager.getPassword()));
        return ResponseEntity.ok(managerConverter.toDto(managerService
                .create(managerConverter.toEntity(manager))));
    }


}
