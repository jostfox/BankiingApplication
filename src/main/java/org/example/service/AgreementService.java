package org.example.service;

import org.example.entity.Agreement;

import java.util.List;

public interface AgreementService {

    public List<Agreement> getAll();

    public Agreement newAgreement(Agreement agreement);
}
