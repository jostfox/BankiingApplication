package org.example.repositories;

import org.example.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

@Repository
public interface ClientRepository extends JpaRepository <Client, Long> {

    Client findByLogin(String login);

}
