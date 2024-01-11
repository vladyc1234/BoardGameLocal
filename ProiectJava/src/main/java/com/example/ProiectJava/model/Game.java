package com.example.ProiectJava.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int gameId;
    private String gameName;
    private String description;
    private Integer nrPlayers;
    private String avgGameTime;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "developer_id")
    private Developer developer;

    @JsonIgnore
    @ManyToMany(mappedBy = "gameList", cascade = CascadeType.ALL)
    private List<BoardGameShop> boardGameShops = new ArrayList<>();

    public Game() {
    }

    public Game(String gameName) {
        this.gameName = gameName;
    }

    public Game(String gameName, String description, Integer nrPlayers, String avgGameTime) {
        this.gameName = gameName;
        this.description = description;
        this.nrPlayers = nrPlayers;
        this.avgGameTime = avgGameTime;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getNrPlayers() {
        return nrPlayers;
    }

    public void setNrPlayers(Integer nrPlayers) {
        this.nrPlayers = nrPlayers;
    }

    public String getAvgGameTime() {
        return avgGameTime;
    }

    public void setAvgGameTime(String avgGameTime) {
        this.avgGameTime = avgGameTime;
    }

    public Developer getDeveloper() {
        return developer;
    }

    public void setDeveloper(Developer developer) {
        this.developer = developer;
    }

    public List<BoardGameShop> getBoardGameShops() {
        return boardGameShops;
    }

    public void setBoardGameShops(List<BoardGameShop> boardGameShops) {
        this.boardGameShops = boardGameShops;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }
}
