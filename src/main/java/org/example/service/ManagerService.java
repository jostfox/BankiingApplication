package org.example.service;

import org.example.entity.Manager;

import java.util.List;

public interface ManagerService {

    public List<Manager> getAll();

    public Manager newManager(Manager manager);
}
