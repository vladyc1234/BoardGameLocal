package com.example.ProiectJava.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class BoardGameShop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int boardGameShopId;
    private String location;
    private String telephone;
    private Double open_time;
    private Double close_time;
    private Double player_fee;

    @JsonIgnore
    @OneToMany(mappedBy = "boardGameShop")
    private List<Event> eventList = new ArrayList<>();

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "game_list",
            joinColumns = @JoinColumn(name = "board_game_shop_id"),
            inverseJoinColumns = @JoinColumn(name = "game_id"))
    private List<Game> gameList = new ArrayList<>();

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "food_drink_menu",
            joinColumns = @JoinColumn(name = "board_game_shop_id"),
            inverseJoinColumns = @JoinColumn(name = "refreshment_id"))
    private List<Refreshment> refreshmentList = new ArrayList<>();


    public BoardGameShop(){

    }

    public BoardGameShop(String location) {
        this.location = location;
    }

    public BoardGameShop(String location, String telephone, Double open_time, Double close_time, Double player_fee) {
        this.location = location;
        this.telephone = telephone;
        this.open_time = open_time;
        this.close_time = close_time;
        this.player_fee = player_fee;
    }

    public int getBoardGameShopId() {
        return boardGameShopId;
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

    public Double getOpen_time() {
        return open_time;
    }

    public void setOpen_time(Double open_time) {
        this.open_time = open_time;
    }

    public Double getClose_time() {
        return close_time;
    }

    public void setClose_time(Double close_time) {
        this.close_time = close_time;
    }

    public Double getPlayer_fee() {
        return player_fee;
    }

    public void setPlayer_fee(Double player_fee) {
        this.player_fee = player_fee;
    }

    public List<Event> getEventList() {
        return eventList;
    }

    public void setEventList(List<Event> eventList) {
        this.eventList = eventList;
    }

    public List<Game> getGameList() {
        return gameList;
    }

    public void setGameList(List<Game> gameList) {
        this.gameList = gameList;
    }

    public List<Refreshment> getRefreshmentList() {
        return refreshmentList;
    }

    public void setRefreshmentList(List<Refreshment> refreshmentList) {
        this.refreshmentList = refreshmentList;
    }
}
