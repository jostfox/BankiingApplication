package org.example.service;

import org.example.entity.Admin;
import org.example.exceptions.ItemNotFoundException;
import org.example.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public List<Admin> getAll() {
        return adminRepository.findAll();
    }

    @Override
    public Admin getByLogin(String login) {
        return adminRepository.findByLogin(login)
                .orElseThrow(() -> new ItemNotFoundException(String.format("Administrator " +
                        "with login \"%s\" not found", login)));
    }

    @Override
    public Admin save(Admin admin) {
        return adminRepository.save(admin);
    }

    @Override
    public void remove(Long id) {
        adminRepository.deleteById(id);
    }

    @Override
    public Admin getCurrent() {
        return getByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
    }
}
