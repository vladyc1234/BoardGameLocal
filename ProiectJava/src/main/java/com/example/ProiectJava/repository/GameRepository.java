package com.example.ProiectJava.repository;

import com.example.ProiectJava.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Integer> {
}

