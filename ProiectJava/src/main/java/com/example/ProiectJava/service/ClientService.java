package com.example.ProiectJava.service;

import com.example.ProiectJava.model.*;
import com.example.ProiectJava.repository.BoardGameShopRepository;
import com.example.ProiectJava.repository.ClientRepository;
import com.example.ProiectJava.repository.EventRepository;
import com.example.ProiectJava.repository.GameRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ClientService {
    private final ClientRepository clientRepository;
    private final EventRepository eventRepository;


    public ClientService(EventRepository eventRepository, ClientRepository clientRepository) {
        this.eventRepository = eventRepository;
        this.clientRepository = clientRepository;
    }

    private boolean isValidPassword(String password) {
        // Minimum 10 characters, at least one uppercase, one lowercase, and one special character
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!*]).{10,}$";

        return password.matches(regex);
    }

    public Client saveClient(Client client) {
        List<Client> clients = clientRepository.findAll();

        List<Client> clientsToReturn = new ArrayList<>();

        for (Client value : clients){
            if(Objects.equals(client.getUserName(), value.getUserName()))
            {
                throw new IllegalStateException("UserName already taken");
            }
        }

        if(!isValidPassword(client.getPassword()))
        {
            throw new IllegalStateException("invalid password");
        }

        return clientRepository.save(client);
    }

    public boolean deleteClient(int developerId) {
        boolean exists = clientRepository.existsById(developerId);
        if (!exists) {
            throw  new IllegalStateException("shop with Id " + developerId + " does not exist");
        }
        else {
            clientRepository.deleteById(developerId);
            return true;
        }

    }

    public List<Client> getClients(){
        List<Client> clients = clientRepository.findAll();

        List<Client> clientsToReturn = new ArrayList<>();

        for (Client value : clients){
            clientsToReturn.add(value);
        }

        return clientsToReturn;
    }

    @Transactional
    public List<Event> getClientEvents(int clientId){
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new IllegalStateException(
                        "Client with id " + clientId + " doesn' t exist "
                ));

        List<Event> events = client.getEventList();

        List<Event> eventsToReturn = new ArrayList<>();

        for (Event value : events){
            eventsToReturn.add(value);
        }

        return eventsToReturn;
    }

    @Transactional
    public boolean updateClient(Integer clientd, Client clientUpdate) {
        Client client = clientRepository.findById(clientd)
                .orElseThrow(() -> new IllegalStateException(
                        "Client with id " + clientd + " doesn' t exist "
                ));
        if(client != null)
        {
            if(clientUpdate.getEmail() != null){
                client.setEmail(clientUpdate.getEmail());
            }

            if(clientUpdate.getPassword() != null){
                client.setPassword(clientUpdate.getPassword());
            }

            if(clientUpdate.getUserName() != null){
                client.setUserName(clientUpdate.getUserName());
            }

            return true;
        }
        else {
            return false;
        }

    }

    @Transactional
    public boolean addToEventList(int clientId, int eventId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new EntityNotFoundException("Client not found with id: " + clientId));

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new EntityNotFoundException("Event not found with id: " + eventId));

        if(client != null && event != null) {
            client.getEventList().add(event);
            event.getClients().add(client);
            return true;
        }
        else
        {
            return false;
        }
    }

}

