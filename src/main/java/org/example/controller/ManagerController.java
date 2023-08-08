package org.example.controller;

import org.example.entity.Manager;
import org.example.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("manager")
public class ManagerController {

    @Autowired
    ManagerService managerService;

    @GetMapping
    List<Manager> getAll() {
        return managerService.getAll();
    }

    @PostMapping
    ResponseEntity<Manager> newManager(@RequestBody Manager manager) {
        return ResponseEntity.ok(managerService.newManager(manager));
    }
}
