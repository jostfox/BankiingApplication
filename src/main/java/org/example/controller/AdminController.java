package org.example.controller;

import org.example.entity.Admin;
import org.example.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AdminService adminService;

    @GetMapping
    @PreAuthorize("hasAuthority('permission:admin')")
    List<Admin> getAll(){
        return adminService.getAll();
    }

    @GetMapping("/{login}")
    @PreAuthorize("hasAuthority('permission:admin')")
    Admin getByLogin(@PathVariable("login") String login){
        return adminService.getByLogin(login);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('permission:admin')")
    ResponseEntity<Admin> add(@RequestBody Admin admin){
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        return ResponseEntity.ok(adminService.save(admin));
    }

    @DeleteMapping
    @PreAuthorize("hasAuthority('permission:admin')")
    public void remove (@PathVariable ("id") Long id){
        adminService.remove(id);
    }

}
