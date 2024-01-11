package com.example.ProiectJava.controller;

import com.example.ProiectJava.model.Event;
import com.example.ProiectJava.model.Game;
import com.example.ProiectJava.service.EventService;
import com.example.ProiectJava.service.GameService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(GameServiceController.class)
public class GameControllerT {
    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper objectMapper= new ObjectMapper();
    @MockBean
    private GameService gameService;
    @Test
    public void saveGame() throws Exception {
        Game game = new Game("Catan", "yay", 6, "30 - 60 min");

        when(gameService.saveGame(any(Game.class), anyInt())).thenReturn(game);

        //act
        //assert
        mockMvc.perform(
                        post("/games/game/new")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(game))
                                .param("developerId", "1")
                ).andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(jsonPath("$.gameName").value(game.getGameName()))
                .andExpect(jsonPath("$.avgGameTime").value(game.getAvgGameTime()))
                .andExpect(jsonPath("$.nrPlayers").value(game.getNrPlayers()))
                .andExpect(jsonPath("$.description").value(game.getDescription()));
    }

    @Test
    public void deleteEventTest() throws Exception {
        // Given
        int gameIdToDelete = 1;
        when(gameService.deleteGame(anyInt())).thenReturn(true);

        // When & Then
        mockMvc.perform(delete("/games/game/delete")
                        .param("gameId", String.valueOf(gameIdToDelete)))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.content().string("Game deleted successfully"));
    }
    @Test
    public void getGameTest() throws Exception {
        // Given
        List<Game> gameList = new ArrayList<>();
        when(gameService.getGames()).thenReturn(gameList);

        // When & Then
        mockMvc.perform(get("/games/game/get"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @Test
    public void updateGameTest() throws Exception {
        int gameIdToUpdate = 1;

        Game game = new Game();

        when(gameService.updateGame(anyInt(), any(Game.class))).thenReturn(true);

        // When & Then
        mockMvc.perform(put("/games/game/update")
                        .param("gameId", String.valueOf(gameIdToUpdate))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(game)))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.content().string("Game updated successfully"));
    }
}
