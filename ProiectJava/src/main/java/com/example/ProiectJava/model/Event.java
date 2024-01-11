package com.example.ProiectJava.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int eventId;
    private Double entry_fee;
    private Double prize_pool;
    private Double start_time;
    private Double end_time;
    private Date planned_date;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "game_id")
    private Game game;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "board_game_shop_id")
    private BoardGameShop boardGameShop;


    @JsonIgnore
    @ManyToMany(mappedBy = "eventList", cascade = CascadeType.ALL)
    private List<Client> clients = new ArrayList<>();

    public Event() {
    }
    public Event(Double entry_fee) {
        this.entry_fee = entry_fee;
    }
    public Event(Double entry_fee, Double prize_pool, Double start_time, Double end_time, Date planned_date) {
        this.entry_fee = entry_fee;
        this.prize_pool = prize_pool;
        this.start_time = start_time;
        this.end_time = end_time;
        this.planned_date = planned_date;
    }

    public Double getEntry_fee() {
        return entry_fee;
    }

    public void setEntry_fee(Double entry_fee) {
        this.entry_fee = entry_fee;
    }

    public Double getPrize_pool() {
        return prize_pool;
    }

    public void setPrize_pool(Double prize_pool) {
        this.prize_pool = prize_pool;
    }

    public Double getStart_time() {
        return start_time;
    }

    public void setStart_time(Double start_time) {
        this.start_time = start_time;
    }

    public Double getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Double end_time) {
        this.end_time = end_time;
    }

    public BoardGameShop getBoardGameShop() {
        return boardGameShop;
    }

    public void setBoardGameShop(BoardGameShop boardGameShop) {
        this.boardGameShop = boardGameShop;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public Date getPlanned_date() {
        return planned_date;
    }

    public void setPlanned_date(Date planned_date) {
        this.planned_date = planned_date;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }
}
