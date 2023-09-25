package org.example.repositories;

import org.example.entity.Admin;
import org.example.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {

    Optional<Admin> findByLogin(String login);

}
