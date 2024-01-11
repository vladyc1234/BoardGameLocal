package com.example.ProiectJava.service;

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
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GameServiceTest {

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
    public void saveGameTest(){
        //arrange
        int developerId = 1;

        Game game = new Game("Catan", "yay", 6, "30 - 60 min");
        Developer developer = new Developer("D&D", "USA", "0700000000");

        when(developerRepository.findById(developerId)).thenReturn(Optional.of(developer));
        when(gameRepository.save(game)).thenReturn(game);


        //act
        Game result = gameService.saveGame(game, developerId);

        //assert
        assertEquals(result.getDeveloper().getDeveloperName(), game.getDeveloper().getDeveloperName(), "Should be equals");
        assertEquals(result.getGameName(), game.getGameName(), "game name should be equals");
        assertEquals(result.getDescription(), game.getDescription(), "game description should be equals");
        assertEquals(result.getNrPlayers(), game.getNrPlayers(), "game player number should be equals");
        assertEquals(result.getAvgGameTime(), game.getAvgGameTime(), "game avg time should be equals");
    }

    @Test
    public void deleteGameTest() {

        Integer gameToDeleteId = 1;

        when(gameRepository.existsById(gameToDeleteId)).thenReturn(true);

        Boolean result = gameService.deleteGame(gameToDeleteId);

        assertEquals(result, true, "Should be equals");
    }
    @Test
    public void getGameTest() {

        Game game = new Game();
        List<Game> shopList = Collections.singletonList(game);

        when(gameRepository.findAll()).thenReturn(shopList);

        List<Game> result = gameService.getGames();

        // Then
        assertEquals(result.isEmpty(), false, "Should not be empty");
        assertEquals(result.size(), 1, "Should have one element");
    }

    @Test
    public void updateGameTest() {

        Game game = new Game();
        Integer gameToUpdateId = 1;

        when(gameRepository.findById(gameToUpdateId)).thenReturn(Optional.of(game));

        Boolean result = gameService.updateGame(gameToUpdateId, game);

        assertEquals(result, true, "Should be equals");
    }
}
