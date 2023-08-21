package org.example.service;

import org.example.entity.Manager;

import java.util.List;

public interface ManagerService {

    public List<Manager> getAll();

    public Manager getById(Long id);

    public Manager getByName(String firstName, String lastName);

    public Manager create(Manager manager);

    public void remove(Long id);

    public Manager update(Long id);


}
