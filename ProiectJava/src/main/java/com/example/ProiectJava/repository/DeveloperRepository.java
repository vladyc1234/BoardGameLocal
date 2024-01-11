package com.example.ProiectJava.repository;

import com.example.ProiectJava.model.Developer;
import com.example.ProiectJava.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeveloperRepository extends JpaRepository<Developer, Integer> {
}
