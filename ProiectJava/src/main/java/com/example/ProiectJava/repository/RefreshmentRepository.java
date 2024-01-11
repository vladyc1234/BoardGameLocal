package com.example.ProiectJava.repository;

import com.example.ProiectJava.model.Event;
import com.example.ProiectJava.model.Refreshment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshmentRepository extends JpaRepository<Refreshment, Integer> {
}