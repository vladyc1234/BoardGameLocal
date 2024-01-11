package com.example.ProiectJava.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Refreshment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int refreshmentId;
    private String name;
    private Double price;

    @JsonIgnore
    @ManyToMany(mappedBy = "refreshmentList", cascade = CascadeType.ALL)
    private List<BoardGameShop> boardGameShops = new ArrayList<>();

    public Refreshment() {
    }
    public Refreshment(String name) {
        this.name = name;
    }
    public Refreshment(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<BoardGameShop> getBoardGameShops() {
        return boardGameShops;
    }

    public void setBoardGameShops(List<BoardGameShop> boardGameShops) {
        this.boardGameShops = boardGameShops;
    }

    public int getRefreshmentId() {
        return refreshmentId;
    }

    public void setRefreshmentId(int refreshmentId) {
        this.refreshmentId = refreshmentId;
    }
}
