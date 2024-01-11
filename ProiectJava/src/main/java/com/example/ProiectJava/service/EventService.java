package com.example.ProiectJava.service;

import com.example.ProiectJava.model.*;
import com.example.ProiectJava.repository.BoardGameShopRepository;
import com.example.ProiectJava.repository.EventRepository;
import com.example.ProiectJava.repository.GameRepository;
import com.example.ProiectJava.repository.RefreshmentRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class EventService {
    private final EventRepository eventRepository;
    private final GameRepository gameRepository;
    private final BoardGameShopRepository boardGameShopRepository;


    public EventService(EventRepository eventRepository, GameRepository gameRepository, BoardGameShopRepository boardGameShopRepository) {
        this.eventRepository = eventRepository;
        this.gameRepository = gameRepository;
        this.boardGameShopRepository = boardGameShopRepository;
    }

    public Event saveEvent(Event event, int gameId, int shopId){
        Game game = gameRepository.findById(gameId).orElseThrow(() ->
                new RuntimeException("invalid game id"));
        BoardGameShop shop = boardGameShopRepository.findById(shopId).orElseThrow(() ->
                new RuntimeException("invalid shop id"));

        event.setGame(game);
        event.setBoardGameShop(shop);

        return eventRepository.save(event);
    }

    public boolean deleteEvent(int developerId) {
        boolean exists = eventRepository.existsById(developerId);
        if (!exists) {
            throw  new IllegalStateException("shop with Id " + developerId + " does not exist");
        }
        else {
            eventRepository.deleteById(developerId);
            return true;
        }

    }

    public List<Event> getEvents(){
        List<Event> events = eventRepository.findAll();

        List<Event> eventsToReturn = new ArrayList<>();

        for (Event value : events){
            eventsToReturn.add(value);
        }

        return eventsToReturn;
    }

    @Transactional
    public List<Refreshment> getBoardGameShopsRefreshments(int shopId){
        BoardGameShop boardGameShop = boardGameShopRepository.findById(shopId)
                .orElseThrow(() -> new IllegalStateException(
                        "Shop with id " + shopId + " doesn' t exist "
                ));

        List<Refreshment> refreshments = boardGameShop.getRefreshmentList();

        List<Refreshment> refreshmentsToReturn = new ArrayList<>();

        for (Refreshment value : refreshments){
            refreshmentsToReturn.add(value);
        }

        return refreshmentsToReturn;
    }

    @Transactional
    public boolean updateEvent(Integer eventd, Event eventUpdate) {
        Event event = eventRepository.findById(eventd)
                .orElseThrow(() -> new IllegalStateException(
                        "Event with id " + eventd + " doesn' t exist "
                ));
        if(event != null)
        {
            if(eventUpdate.getEnd_time() != null){
                event.setEnd_time(eventUpdate.getEnd_time());
            }

            if(eventUpdate.getPrize_pool() != null){
                event.setPrize_pool(eventUpdate.getPrize_pool());
            }

            if(eventUpdate.getStart_time() != null){
                event.setStart_time(eventUpdate.getStart_time());
            }

            if(eventUpdate.getEntry_fee() != null){
                event.setEntry_fee(eventUpdate.getEntry_fee());
            }

            if(eventUpdate.getPlanned_date() != null){
                event.setPlanned_date(eventUpdate.getPlanned_date());
            }

            return true;
        }
        else {
            return false;
        }

    }

}
