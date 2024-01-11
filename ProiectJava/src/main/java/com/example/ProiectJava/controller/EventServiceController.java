package com.example.ProiectJava.controller;

import com.example.ProiectJava.model.Developer;
import com.example.ProiectJava.model.Event;
import com.example.ProiectJava.service.BoardGameShopService;
import com.example.ProiectJava.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventServiceController {

    @Autowired
    private EventService eventService;

    @PostMapping("/event/new")
    public ResponseEntity<Event> saveEvent(@RequestBody Event event,
                                           @RequestParam int gameId,
                                           @RequestParam int shopId){
        return ResponseEntity.ok()
                .body(eventService.saveEvent(event, gameId, shopId));
    }
    @DeleteMapping("/event/delete")
    public ResponseEntity<String> deleteEvent(@RequestParam Integer eventId) {
        boolean deleted = eventService.deleteEvent(eventId);

        if (deleted) {
            return ResponseEntity.ok().body("Event deleted successfully");
        } else {
            return ResponseEntity.status(404).body("Event not found or could not be deleted");
        }
    }

    @GetMapping("/event/get")
    public ResponseEntity<List<Event>> getEvents(){
        return ResponseEntity.ok().body(eventService.getEvents());
    }

    @PutMapping("/event/update")
    public ResponseEntity<String> updateEvent(@RequestParam Integer eventId,
                                                  @RequestBody Event event){
        boolean updated = eventService.updateEvent(eventId, event);
        if (updated) {
            return ResponseEntity.ok().body("Event updated successfully");
        } else {
            return ResponseEntity.status(404).body("Event not found or could not be updated");
        }
    }
}
