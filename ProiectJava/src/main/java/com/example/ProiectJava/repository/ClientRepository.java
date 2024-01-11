package com.example.ProiectJava.repository;

import com.example.ProiectJava.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {
}
