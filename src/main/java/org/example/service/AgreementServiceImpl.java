package org.example.service;

import org.example.entity.Agreement;
import org.example.exceptions.ItemNotFoundException;
import org.example.repositories.AgreementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("agreementService")
public class AgreementServiceImpl implements AgreementService {

    private final AgreementRepository agreementRepository;

    @Autowired
    public AgreementServiceImpl(AgreementRepository agreementRepository) {
        this.agreementRepository = agreementRepository;
    }

    @Override
    public List<Agreement> getAll() {
        return agreementRepository.findAll();
    }

    @Override
    public Agreement create(Agreement agreement) {
        return agreementRepository.save(agreement);
    }

    @Override
    public Agreement getById(Long id) {
        Agreement agreement = agreementRepository.findById(id).orElse(null);
        if (agreement == null){
            throw new ItemNotFoundException(String.format("Agreement with id %d not found", id));
        }
        return agreementRepository.getReferenceById(id);
    }

    @Override
    public void remove(Long id) {
        agreementRepository.deleteById(id);
    }
}
