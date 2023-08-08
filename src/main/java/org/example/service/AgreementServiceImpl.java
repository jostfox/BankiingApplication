package org.example.service;

import org.example.entity.Agreement;
import org.example.repositories.AgreementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgreementServiceImpl implements AgreementService {

    @Autowired
    AgreementRepository agreementRepository;

    @Override
    public List<Agreement> getAll() {
        return agreementRepository.findAll();
    }

    @Override
    public Agreement newAgreement(Agreement agreement) {
        return agreementRepository.save(agreement);
    }
}
