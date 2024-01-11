package com.example.ProiectJava.controller;

import com.example.ProiectJava.model.BoardGameShop;
import com.example.ProiectJava.model.Developer;
import com.example.ProiectJava.service.DeveloperService;
import com.example.ProiectJava.service.RefreshmentService;
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

@WebMvcTest(DeveloperServiceController.class)
public class DeveloperControllerT {
    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper objectMapper= new ObjectMapper();
    @MockBean
    private DeveloperService developerService;

    @Test
    public void saveDeveloper() throws Exception {
        Developer developer = new Developer("D&D", "USA", "0700000000");

        when(developerService.saveDeveloper(any(Developer.class))).thenReturn(developer);

        //act
        //assert
        mockMvc.perform(
                        post("/developers/developer/new")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(developer))
                ).andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(jsonPath("$.developerName").value(developer.getDeveloperName()))
                .andExpect(jsonPath("$.location").value(developer.getLocation()))
                .andExpect(jsonPath("$.telephone").value(developer.getTelephone()));
    }

    @Test
    public void deleteDeveloperTest() throws Exception {
        // Given
        int devIdToDelete = 1;
        when(developerService.deleteDeveloper(anyInt())).thenReturn(true);

        // When & Then
        mockMvc.perform(delete("/developers/developer/delete")
                        .param("developerId", String.valueOf(devIdToDelete)))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.content().string("Developer deleted successfully"));
    }
    @Test
    public void getDeveloperTest() throws Exception {
        // Given
        List<Developer> developerList = new ArrayList<>();
        when(developerService.getDevelopers()).thenReturn(developerList);

        // When & Then
        mockMvc.perform(get("/developers/developer/get"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @Test
    public void updateDeveloperTest() throws Exception {
        int developerIdToUpdate = 1;

        Developer developer = new Developer();

        when(developerService.updateDeveloper(anyInt(), any(Developer.class))).thenReturn(true);

        // When & Then
        mockMvc.perform(put("/developers/developer/update")
                        .param("developerId", String.valueOf(developerIdToUpdate))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(developer)))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.content().string("Developer updated successfully"));
    }
}
