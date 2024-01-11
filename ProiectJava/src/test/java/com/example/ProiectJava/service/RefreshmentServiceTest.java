package com.example.ProiectJava.service;

import com.example.ProiectJava.model.Game;
import com.example.ProiectJava.model.Refreshment;
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
public class RefreshmentServiceTest {

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
    public void saveRefreshmentTest(){
        //arrange
        Refreshment refreshment = new Refreshment("water", 1.0);
//        Artist artistResponse = new Artist("response artist");
        when(refreshmentRepository.save(refreshment)).thenReturn(refreshment);

        //act
        Refreshment result = refreshmentService.saveRefreshment(refreshment);

        //assert
        assertEquals(refreshment.getName(), result.getName(), "refreshment name equal");
        assertEquals(refreshment.getPrice(), result.getPrice(), "refreshment price equal");
    }

    @Test
    public void deleteRefreshmentTest() {

        Integer refreshmentToDeleteId = 1;

        when(refreshmentRepository.existsById(refreshmentToDeleteId)).thenReturn(true);

        Boolean result = refreshmentService.deleteRefreshment(refreshmentToDeleteId);

        assertEquals(result, true, "Should be equals");
    }
    @Test
    public void getRefreshmentTest() {

        Refreshment refreshment = new Refreshment();
        List<Refreshment> shopList = Collections.singletonList(refreshment);

        when(refreshmentRepository.findAll()).thenReturn(shopList);

        List<Refreshment> result = refreshmentService.getRefreshments();

        // Then
        assertEquals(result.isEmpty(), false, "Should not be empty");
        assertEquals(result.size(), 1, "Should have one element");
    }

    @Test
    public void updateRefreshmentTest() {

        Refreshment refreshment = new Refreshment();
        Integer refreshmentToUpdateId = 1;

        when(refreshmentRepository.findById(refreshmentToUpdateId)).thenReturn(Optional.of(refreshment));

        Boolean result = refreshmentService.updateRefreshment(refreshmentToUpdateId, refreshment);

        assertEquals(result, true, "Should be equals");
    }
}
