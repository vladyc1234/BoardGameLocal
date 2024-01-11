package com.example.ProiectJava.service;

import com.example.ProiectJava.model.BoardGameShop;
import com.example.ProiectJava.model.Developer;
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
public class DeveloperServiceTest {

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
    public void saveDeveloperTest(){
        //arrange
        Developer developer = new Developer("D&D", "USA", "0700000000");

        when(developerRepository.save(developer)).thenReturn(developer);

        //act
        Developer result = developerService.saveDeveloper(developer);

        //assert
        assertEquals(developer.getDeveloperName(), result.getDeveloperName(), "dev name equal");
        assertEquals(developer.getLocation(), result.getLocation(), "dev location equal");
        assertEquals(developer.getTelephone(), result.getTelephone(), "dev telephone equal");
    }

    @Test
    public void deleteDeveloperTest() {

        Integer devToDeleteId = 1;

        when(developerRepository.existsById(devToDeleteId)).thenReturn(true);

        Boolean result = developerService.deleteDeveloper(devToDeleteId);

        assertEquals(result, true, "Should be equals");
    }
    @Test
    public void getDeveloperTest() {

        Developer developer = new Developer();
        List<Developer> shopList = Collections.singletonList(developer);

        when(developerRepository.findAll()).thenReturn(shopList);

        List<Developer> result = developerService.getDevelopers();

        // Then
        assertEquals(result.isEmpty(), false, "Should not be empty");
        assertEquals(result.size(), 1, "Should have one element");
    }

    @Test
    public void updateDeveloperTest() {

        Developer developer = new Developer();
        Integer developerToUpdateId = 1;

        when(developerRepository.findById(developerToUpdateId)).thenReturn(Optional.of(developer));

        Boolean result = developerService.updateDeveloper(developerToUpdateId, developer);

        assertEquals(result, true, "Should be equals");
    }

}
