package org.example.service;

import org.example.entity.Manager;
import org.example.repositories.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    ManagerRepository managerRepository;

    @Override
    public List<Manager> getAll() {
        return managerRepository.findAll();
    }

    @Override
    public Manager newManager(Manager manager) {
        return managerRepository.save(manager);
    }
}
