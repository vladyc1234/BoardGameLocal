package com.example.ProiectJava.controller;

import com.example.ProiectJava.model.Game;
import com.example.ProiectJava.model.Refreshment;
import com.example.ProiectJava.service.GameService;
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

@WebMvcTest(RefreshmentServiceController.class)
public class RefreshmentControllerT {
    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper objectMapper= new ObjectMapper();
    @MockBean
    private RefreshmentService refreshmentService;
    @Test
    public void saveRefreshment() throws Exception {
        Refreshment refreshment = new Refreshment("water", 1.0);

        when(refreshmentService.saveRefreshment(any(Refreshment.class))).thenReturn(refreshment);

        //act
        //assert
        mockMvc.perform(
                        post("/refreshments/refreshment/new")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(refreshment))
                ).andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(jsonPath("$.name").value(refreshment.getName()))
                .andExpect(jsonPath("$.price").value(refreshment.getPrice()));
    }

    @Test
    public void deleteEventTest() throws Exception {
        // Given
        int refreshmentIdToDelete = 1;
        when(refreshmentService.deleteRefreshment(anyInt())).thenReturn(true);

        // When & Then
        mockMvc.perform(delete("/refreshments/refreshment/delete")
                        .param("refreshmentId", String.valueOf(refreshmentIdToDelete)))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.content().string("Refreshment deleted successfully"));
    }
    @Test
    public void getRefreshmentTest() throws Exception {
        // Given
        List<Refreshment> refreshmentList = new ArrayList<>();
        when(refreshmentService.getRefreshments()).thenReturn(refreshmentList);

        // When & Then
        mockMvc.perform(get("/refreshments/refreshment/get"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @Test
    public void updateRefreshmentTest() throws Exception {
        int refreshmentIdToUpdate = 1;

        Refreshment refreshment = new Refreshment();

        when(refreshmentService.updateRefreshment(anyInt(), any(Refreshment.class))).thenReturn(true);

        // When & Then
        mockMvc.perform(put("/refreshments/refreshment/update")
                        .param("refreshmentId", String.valueOf(refreshmentIdToUpdate))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(refreshment)))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.content().string("Refreshment updated successfully"));
    }
}
