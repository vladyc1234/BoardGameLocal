package com.example.ProiectJava.controller;

import com.example.ProiectJava.model.BoardGameShop;
import com.example.ProiectJava.model.Developer;
import com.example.ProiectJava.model.Game;
import com.example.ProiectJava.model.Refreshment;
import com.example.ProiectJava.repository.*;
import com.example.ProiectJava.service.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.lenient;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(BoardGameShopServiceController.class)
public class BoardGameShopControllerT {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper= new ObjectMapper();

    @MockBean
    private BoardGameShopService boardGameShopService;
    @MockBean
    private GameService gameService;
    @MockBean
    private RefreshmentService refreshmentService;
    @MockBean
    private EventService eventService;

    @MockBean
    private GameRepository gameRepository;

    @MockBean
    private DeveloperRepository developerRepository;

    @MockBean
    private EventRepository eventRepository;

    @MockBean
    private RefreshmentRepository refreshmentRepository;

    @MockBean
    private BoardGameShopRepository boardGameShopRepository;

    @Test
    public void saveBoardGameShopTest() throws Exception{

        BoardGameShop boardGameShop = new BoardGameShop("America", "07000000", 10.00, 24.00, 15.0);

        when(boardGameShopService.saveBoardGameShop(any(BoardGameShop.class))).thenReturn(boardGameShop);

        mockMvc.perform(
                        post("/boardGameShops/shop/new")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(boardGameShop)))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(jsonPath("$.close_time").value(boardGameShop.getClose_time()))
                .andExpect(jsonPath("$.open_time").value(boardGameShop.getOpen_time()))
                .andExpect(jsonPath("$.player_fee").value(boardGameShop.getPlayer_fee()))
                .andExpect(jsonPath("$.location").value(boardGameShop.getLocation()))
                .andExpect(jsonPath("$.telephone").value(boardGameShop.getTelephone()));
    }

    @Test
    public void deleteBoardGameShopTest() throws Exception {
        // Given
        int shopIdToDelete = 1;
        when(boardGameShopService.deleteBoardGameShop(anyInt())).thenReturn(true);

        // When & Then
        mockMvc.perform(delete("/boardGameShops/shop/delete")
                        .param("shopId", String.valueOf(shopIdToDelete)))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.content().string("BoardGameShop deleted successfully"));
    }

    @Test
    public void getBoardGameShopTest() throws Exception {
        // Given
        List<BoardGameShop> shopList = new ArrayList<>();
        when(boardGameShopService.getBoardGameShops()).thenReturn(shopList);

        // When & Then
        mockMvc.perform(get("/boardGameShops/shop/get"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @Test
    public void updateBoardGameShopTest() throws Exception {
        int shopIdToUpdate = 1;

        BoardGameShop boardGameShop = new BoardGameShop("America", "07000000", 10.00, 24.00, 15.0);

        when(boardGameShopService.updateBoardGameShop(anyInt(), any(BoardGameShop.class))).thenReturn(true);

        // When & Then
        mockMvc.perform(put("/boardGameShops/shop/update")
                        .param("shopId", String.valueOf(shopIdToUpdate))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(boardGameShop)))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.content().string("BoardGameShop updated successfully"));
    }


}
