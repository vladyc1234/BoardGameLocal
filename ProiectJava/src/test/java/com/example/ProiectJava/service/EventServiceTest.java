package com.example.ProiectJava.service;

import com.example.ProiectJava.model.BoardGameShop;
import com.example.ProiectJava.model.Developer;
import com.example.ProiectJava.model.Event;
import com.example.ProiectJava.model.Game;
import com.example.ProiectJava.repository.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EventServiceTest {

    @InjectMocks
    private BoardGameShopService boardGameShopService;
    @InjectMocks
    private DeveloperService developerService;
    @InjectMocks
    private GameService gameService;
    @InjectMocks
    private RefreshmentService refreshmentService;
    @InjectMocks
    private EventService eventService;

    @Mock
    private GameRepository gameRepository;

    @Mock
    private DeveloperRepository developerRepository;

    @Mock
    private EventRepository eventRepository;

    @Mock
    private RefreshmentRepository refreshmentRepository;

    @Mock
    private BoardGameShopRepository boardGameShopRepository;

    @Test
    public void saveEventTest(){
        int gameId = 1;
        int shopId = 1;

        Event event = new Event(1.0, 10.0, 10.00, 20.30, new Date(2024,12,10));
        Game game = new Game("Catan", "yay", 6, "30 - 60 min");
        BoardGameShop boardGameShop = new BoardGameShop("America", "07000000", 10.00, 24.00, 15.0);

        when(gameRepository.findById(gameId)).thenReturn(Optional.of(game));
        when(boardGameShopRepository.findById(shopId)).thenReturn(Optional.of(boardGameShop));
        when(eventRepository.save(event)).thenReturn(event);

        Event result = eventService.saveEvent(event, gameId, shopId);

        //assert
        assertEquals(result.getGame().getGameName(), event.getGame().getGameName(), "Should be equals");
        assertEquals(result.getEntry_fee(), event.getEntry_fee(), "event entry fee should be equals");
        assertEquals(result.getPrize_pool(), event.getPrize_pool(), "event prize pool should be equals");
        assertEquals(result.getStart_time(), event.getStart_time(), "event start time should be equals");
        assertEquals(result.getEnd_time(), event.getEnd_time(), "event end time should be equals");
    }

    @Test
    public void deleteEventTest() {

        Integer eventToDeleteId = 1;

        when(eventRepository.existsById(eventToDeleteId)).thenReturn(true);

        Boolean result = eventService.deleteEvent(eventToDeleteId);

        assertEquals(result, true, "Should be equals");
    }
    @Test
    public void getEventTest() {

        Event event = new Event();
        List<Event> shopList = Collections.singletonList(event);

        when(eventRepository.findAll()).thenReturn(shopList);

        List<Event> result = eventService.getEvents();

        // Then
        assertEquals(result.isEmpty(), false, "Should not be empty");
        assertEquals(result.size(), 1, "Should have one element");
    }

    @Test
    public void updateEventTest() {

        Event event = new Event();
        Integer eventToUpdateId = 1;

        when(eventRepository.findById(eventToUpdateId)).thenReturn(Optional.of(event));

        Boolean result = eventService.updateEvent(eventToUpdateId, event);

        assertEquals(result, true, "Should be equals");
    }
}
