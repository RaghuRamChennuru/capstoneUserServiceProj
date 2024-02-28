package com.ram.capstoneuserserviceproj.Security.Repositry;

import java.util.Optional;


import com.ram.capstoneuserserviceproj.Security.Models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, String> {
    Optional<Client> findByClientId(String clientId);
}