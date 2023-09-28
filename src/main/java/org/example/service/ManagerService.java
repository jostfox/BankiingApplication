package org.example.service;

import org.example.entity.Manager;

import java.util.List;

public interface ManagerService {

    List<Manager> getAll();

    Manager getById(Long id);

    Manager getByName(String firstName, String lastName);

    Manager getByLogin(String login);

    Manager create(Manager manager);

    void remove(Long id);

    Manager update(Long id);


}
