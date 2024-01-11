package com.example.ProiectJava.service;


import com.example.ProiectJava.model.*;
import com.example.ProiectJava.repository.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BoardGameShopServiceTest {
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
    public void saveBoardGameShopTest(){

        BoardGameShop boardGameShop = new BoardGameShop("America", "07000000", 10.00, 24.00, 15.0);

        when(boardGameShopRepository.save(boardGameShop)).thenReturn(boardGameShop);

        BoardGameShop result = boardGameShopService.saveBoardGameShop(boardGameShop);

        //assert
        assertEquals(result.getGameList(), boardGameShop.getGameList(), "Should be equals");
        assertEquals(result.getRefreshmentList(), boardGameShop.getRefreshmentList(), "Should be equals");
        assertEquals(result.getLocation(), boardGameShop.getLocation(), "Should be equals");
        assertEquals(result.getClose_time(), boardGameShop.getClose_time(), "Should be equals");
        assertEquals(result.getOpen_time(), boardGameShop.getOpen_time(), "Should be equals");
        assertEquals(result.getTelephone(), boardGameShop.getTelephone(), "Should be equals");
    }

    @Test
    public void deleteBoardGameShopTest() {

        Integer shopToDeleteId = 1;

        when(boardGameShopRepository.existsById(shopToDeleteId)).thenReturn(true);

        Boolean result = boardGameShopService.deleteBoardGameShop(shopToDeleteId);

        assertEquals(result, true, "Should be equals");
    }

    @Test
    public void getBoardGameShopTest() {

        BoardGameShop boardGameShop = new BoardGameShop("America", "07000000", 10.00, 24.00, 15.0);
        List<BoardGameShop> shopList = Collections.singletonList(boardGameShop);

        when(boardGameShopRepository.findAll()).thenReturn(shopList);

        List<BoardGameShop> result = boardGameShopService.getBoardGameShops();

        // Then
        assertEquals(result.isEmpty(), false, "Should not be empty");
        assertEquals(result.size(), 1, "Should have one element");
    }

    @Test
    public void updateBoardGameShopTest() {

        BoardGameShop boardGameShop = new BoardGameShop("America", "07000000", 10.00, 24.00, 15.0);
        Integer shopToUpdateId = 1;

        when(boardGameShopRepository.findById(shopToUpdateId)).thenReturn(Optional.of(boardGameShop));

        Boolean result = boardGameShopService.updateBoardGameShop(shopToUpdateId, boardGameShop);

        assertEquals(result, true, "Should be equals");
    }

}
