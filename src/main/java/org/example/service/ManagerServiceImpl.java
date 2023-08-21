package org.example.service;

import org.example.entity.Client;
import org.example.entity.Manager;
import org.example.exceptions.ItemNotFoundException;
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
    public Manager create(Manager manager) {
        return managerRepository.save(manager);
    }

    @Override
    public Manager getById(Long id) {
        Manager manager = managerRepository.findById(id).orElse(null);
        if (manager == null){
            throw new ItemNotFoundException(String.format("Manager with id %d not found", id));
        }
        return managerRepository.getReferenceById(id);
    }

    @Override
    public Manager getByName(String firstName, String lastName) {
        return null;
    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public Manager update(Long id) {
        return null;
    }
}
