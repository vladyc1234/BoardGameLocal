package com.example.ProiectJava.controller;


import com.example.ProiectJava.model.*;
import com.example.ProiectJava.repository.BoardGameShopRepository;
import com.example.ProiectJava.repository.ClientRepository;
import com.example.ProiectJava.service.BoardGameShopService;
import com.example.ProiectJava.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientServiceController {

    @Autowired
    private ClientService clientService;
    @Autowired
    private ClientRepository clientRepository;

    @PostMapping("/client/new")
    public ResponseEntity<Client> saveClient(@RequestBody Client client){
        return ResponseEntity.ok().body(clientService.saveClient(client));
    }

    @DeleteMapping("/client/delete")
    public ResponseEntity<String> deleteClient(@RequestParam Integer clientId) {
        boolean deleted = clientService.deleteClient(clientId);

        if (deleted) {
            return ResponseEntity.ok().body("Client deleted successfully");
        } else {
            return ResponseEntity.status(404).body("Client not found or could not be deleted");
        }
    }

    @GetMapping("/client/get")
    public ResponseEntity<List<Client>> getClients(){
        return ResponseEntity.ok().body(clientService.getClients());
    }

    @GetMapping("/client/getEvents")
    public ResponseEntity<List<Event>> getClientEvents(@RequestParam Integer clientId){
        return ResponseEntity.ok().body(clientService.getClientEvents(clientId));
    }

    @PutMapping("/client/update")
    public ResponseEntity<String> updateClient(@RequestParam Integer clientId,
                                                      @RequestBody Client client){
        boolean updated = clientService.updateClient(clientId, client);
        if (updated) {
            return ResponseEntity.ok().body("Client updated successfully");
        } else {
            return ResponseEntity.status(404).body("Client not found or could not be updated");
        }
    }

    @PutMapping("/client/addEvent")
    public ResponseEntity<String> addToEventList(@RequestParam Integer clientId,
                                                @RequestParam Integer eventId){
        boolean updated = clientService.addToEventList(clientId, eventId);
        if (updated) {
            return ResponseEntity.ok().body("Client updated successfully");
        } else {
            return ResponseEntity.status(404).body("Client not found or could not be updated");
        }
    }



}