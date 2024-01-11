package com.example.ProiectJava;

import com.example.ProiectJava.controller.RefreshmentServiceController;
import com.example.ProiectJava.model.Refreshment;
import com.example.ProiectJava.service.RefreshmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class ProiectJavaApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ProiectJavaApplication.class, args);
    }

    public void run(String... args) throws Exception {

    }
}
