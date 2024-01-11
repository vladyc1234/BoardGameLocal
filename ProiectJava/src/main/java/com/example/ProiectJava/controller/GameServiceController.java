package com.example.ProiectJava.controller;

import com.example.ProiectJava.model.Event;
import com.example.ProiectJava.model.Game;
import com.example.ProiectJava.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/games")
public class GameServiceController {

    @Autowired
    private GameService gameService;

    @PostMapping("/game/new")
    public ResponseEntity<Game> saveGame(@RequestBody Game game,
                                         @RequestParam int developerId){
        return ResponseEntity.ok()
                .body(gameService.saveGame(game, developerId));
    }
    @DeleteMapping("/game/delete")
    public ResponseEntity<String> deleteGame(@RequestParam Integer gameId) {
        boolean deleted = gameService.deleteGame(gameId);

        if (deleted) {
            return ResponseEntity.ok().body("Game deleted successfully");
        } else {
            return ResponseEntity.status(404).body("Game not found or could not be deleted");
        }
    }
    @GetMapping("/game/get")
    public ResponseEntity<List<Game>> getGames(){
        return ResponseEntity.ok().body(gameService.getGames());
    }

    @PutMapping("/game/update")
    public ResponseEntity<String> updateGame(@RequestParam Integer gameId,
                                              @RequestBody Game game){
        boolean updated = gameService.updateGame(gameId, game);
        if (updated) {
            return ResponseEntity.ok().body("Game updated successfully");
        } else {
            return ResponseEntity.status(404).body("Game not found or could not be updated");
        }
    }
}
