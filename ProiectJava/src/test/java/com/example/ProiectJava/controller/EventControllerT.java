package com.example.ProiectJava.controller;

import com.example.ProiectJava.model.Developer;
import com.example.ProiectJava.model.Event;
import com.example.ProiectJava.service.DeveloperService;
import com.example.ProiectJava.service.EventService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(EventServiceController.class)
public class EventControllerT {
    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper objectMapper= new ObjectMapper();
    @MockBean
    private EventService eventService;
    @Test
    public void saveEvent() throws Exception {
        Event event = new Event(1.0, 10.0, 10.00, 20.30, new Date(2024,12,10));

        when(eventService.saveEvent(any(Event.class), anyInt(), anyInt())).thenReturn(event);

        //act
        //assert
        mockMvc.perform(
                        post("/events/event/new")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(event))
                                .param("gameId", "1")
                                .param("shopId", "1")
                ).andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(jsonPath("$.end_time").value(event.getEnd_time()))
                .andExpect(jsonPath("$.start_time").value(event.getStart_time()))
                .andExpect(jsonPath("$.entry_fee").value(event.getEntry_fee()))
                .andExpect(jsonPath("$.prize_pool").value(event.getPrize_pool()));
    }

    @Test
    public void deleteEventTest() throws Exception {
        // Given
        int eventIdToDelete = 1;
        when(eventService.deleteEvent(anyInt())).thenReturn(true);

        // When & Then
        mockMvc.perform(delete("/events/event/delete")
                        .param("eventId", String.valueOf(eventIdToDelete)))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.content().string("Event deleted successfully"));
    }
    @Test
    public void getEventTest() throws Exception {
        // Given
        List<Event> eventList = new ArrayList<>();
        when(eventService.getEvents()).thenReturn(eventList);

        // When & Then
        mockMvc.perform(get("/events/event/get"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @Test
    public void updateEventTest() throws Exception {
        int eventIdToUpdate = 1;

        Event event = new Event();

        when(eventService.updateEvent(anyInt(), any(Event.class))).thenReturn(true);

        // When & Then
        mockMvc.perform(put("/events/event/update")
                        .param("eventId", String.valueOf(eventIdToUpdate))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(event)))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.content().string("Event updated successfully"));
    }
}
