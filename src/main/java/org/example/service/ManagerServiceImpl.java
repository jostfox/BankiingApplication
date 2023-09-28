package org.example.service;

import org.example.entity.Manager;
import org.example.exceptions.ItemNotFoundException;
import org.example.repositories.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("managerService")
public class ManagerServiceImpl implements ManagerService {

    private final ManagerRepository managerRepository;

    @Autowired
    public ManagerServiceImpl(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }

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
        return managerRepository.findById(id).orElseThrow(() ->
                new ItemNotFoundException(String.format("Manager with id %d not found", id)));
    }

    @Override
    public Manager getByName(String firstName, String lastName) {
        return getAll().stream().filter(manager -> manager.getFirstName().equals(firstName) &
                        manager.getLastName().equals(lastName))
                .findFirst().orElseThrow(() -> new ItemNotFoundException(String.format("Manager " +
                        "%s %s not found", firstName, lastName)));
    }

    @Override
    public Manager getByLogin(String login) {
        return getAll().stream().filter(manager -> manager.getLogin().equals(login))
                .findFirst().orElseThrow(() -> new ItemNotFoundException(String.format("Manager " +
                        "with login \"%s\" not found", login)));
    }

    @Override
    public void remove(Long id) {
        managerRepository.delete(getById(id));
    }

    @Override
    public Manager update(Long id) {
        throw new UnsupportedOperationException();
    }
}
