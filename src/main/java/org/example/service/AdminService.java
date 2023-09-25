package org.example.service;

import org.example.entity.Admin;
import org.example.entity.Client;

import java.util.List;

public interface AdminService {

    public List<Admin> getAll();

    public Admin getByLogin(String login);

    public Admin save (Admin admin);

    public void remove(Long id);

    public Admin getCurrent();
}
