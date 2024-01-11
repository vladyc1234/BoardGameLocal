package com.example.ProiectJava.service;

import com.example.ProiectJava.model.Developer;
import com.example.ProiectJava.model.Event;
import com.example.ProiectJava.model.Game;
import com.example.ProiectJava.repository.*;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class GameService {

    private final GameRepository gameRepository;
    private final DeveloperRepository developerRepository;

    public GameService(GameRepository gameRepository, DeveloperRepository developerRepository) {
        this.gameRepository = gameRepository;
        this.developerRepository = developerRepository;

    }

    public Game saveGame(Game game, int developerId){
        Developer developer = developerRepository.findById(developerId).orElseThrow(() ->
                new RuntimeException("invalid developer id"));

        game.setDeveloper(developer);

        return gameRepository.save(game);
    }

    public boolean deleteGame(int gameId) {
        boolean exists = gameRepository.existsById(gameId);
        if (!exists) {
            throw  new IllegalStateException("shop with Id " + gameId + " does not exist");
        }
        else {
            gameRepository.deleteById(gameId);
            return true;
        }

    }

    public List<Game> getGames(){
        List<Game> games = gameRepository.findAll();

        List<Game> gamesToReturn = new ArrayList<>();

        for (Game value : games){
            gamesToReturn.add(value);
        }

        return gamesToReturn;
    }

    @Transactional
    public boolean updateGame(Integer gameId, Game gameUpdate) {
        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new IllegalStateException(
                        "Game with id " + gameId + " doesn' t exist "
                ));
        if(game != null)
        {
            if(gameUpdate.getGameName() != null && !gameUpdate.getGameName().isEmpty()){
                game.setGameName(gameUpdate.getGameName());
            }

            if(gameUpdate.getAvgGameTime() != null && !gameUpdate.getAvgGameTime().isEmpty()){
                game.setAvgGameTime(gameUpdate.getAvgGameTime());
            }

            if(gameUpdate.getDescription() != null && !gameUpdate.getDescription().isEmpty()){
                game.setDescription(gameUpdate.getDescription());
            }

            if(gameUpdate.getNrPlayers() != null){
                game.setNrPlayers(gameUpdate.getNrPlayers());
            }

            return true;
        }
        else {
            return false;
        }

    }
}
