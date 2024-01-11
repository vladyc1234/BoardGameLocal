package com.example.ProiectJava.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
public class Developer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int developerId;
    private String developerName;
    private String location;
    private String telephone;

    @JsonIgnore
    @OneToMany(mappedBy = "developer", cascade = CascadeType.ALL)
    private List<Game> gameList = new ArrayList<>();

    public Developer() {
    }

    public Developer(String developerName) {
        this.developerName = developerName;
    }

    public Developer(String developerName, String location, String telephone) {
        this.developerName = developerName;
        this.location = location;
        this.telephone = telephone;
    }

    public String getDeveloperName() {
        return developerName;
    }

    public void setDeveloperName(String developerName) {
        this.developerName = developerName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public List<Game> getGameList() {
        return gameList;
    }

    public void setGameList(List<Game> gameList) {
        this.gameList = gameList;
    }

    public int getDeveloperId() {
        return developerId;
    }

    public void setDeveloperId(int developerId) {
        this.developerId = developerId;
    }
}
