package org.example.service;

import org.example.entity.Agreement;

import java.util.List;

public interface AgreementService {

    public List<Agreement> getAll();

    public Agreement getById(Long id);

    public Agreement create(Agreement agreement);

    public void remove(Long id);

    public Agreement update(Long id);
}
