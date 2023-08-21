package org.example.controller;

import org.example.entity.Agreement;
import org.example.service.AgreementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("agreements")
public class AgreementController {

    @Autowired
    AgreementService agreementService;

    @GetMapping
    List<Agreement> getAll() {
        return agreementService.getAll();
    }

    @PostMapping
    ResponseEntity<Agreement> newAgreement(@RequestBody Agreement agreement) {
        return ResponseEntity.ok(agreementService.create(agreement));
    }
}
