package com.example.ProiectJava.controller;

import com.example.ProiectJava.model.*;
import com.example.ProiectJava.repository.BoardGameShopRepository;
import com.example.ProiectJava.service.BoardGameShopService;
import com.example.ProiectJava.service.DeveloperService;
import com.example.ProiectJava.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/boardGameShops")
public class BoardGameShopServiceController {

    @Autowired
    private BoardGameShopService boardGameShopService;
    @Autowired
    private BoardGameShopRepository boardGameShopRepository;

    @PostMapping("/shop/new")
    public ResponseEntity<BoardGameShop> saveBoardGameShop(@RequestBody BoardGameShop boardGameShop){
        return ResponseEntity.ok().body(boardGameShopService.saveBoardGameShop(boardGameShop));
    }

    @DeleteMapping("/shop/delete")
    public ResponseEntity<String> deleteBoardGameShop(@RequestParam Integer shopId) {
        boolean deleted = boardGameShopService.deleteBoardGameShop(shopId);

        if (deleted) {
            return ResponseEntity.ok().body("BoardGameShop deleted successfully");
        } else {
            return ResponseEntity.status(404).body("BoardGameShop not found or could not be deleted");
        }
    }

    @GetMapping("/shop/get")
    public ResponseEntity<List<BoardGameShop>> getBoardGameShops(){
        return ResponseEntity.ok().body(boardGameShopService.getBoardGameShops());
    }

    @GetMapping("/shop/getGames")
    public ResponseEntity<List<Game>> getBoardGameShopsGames(@RequestParam Integer shopId){
        return ResponseEntity.ok().body(boardGameShopService.getBoardGameShopsGames(shopId));
    }

    @GetMapping("/shop/getRefreshments")
    public ResponseEntity<List<Refreshment>> getBoardGameShopsRefreshments(@RequestParam Integer shopId){
        return ResponseEntity.ok().body(boardGameShopService.getBoardGameShopsRefreshments(shopId));
    }

    @PutMapping("/shop/update")
    public ResponseEntity<String> updateBoardGameShop(@RequestParam Integer shopId,
                                                      @RequestBody BoardGameShop boardGameShop){
        boolean updated = boardGameShopService.updateBoardGameShop(shopId, boardGameShop);
        if (updated) {
            return ResponseEntity.ok().body("BoardGameShop updated successfully");
        } else {
            return ResponseEntity.status(404).body("BoardGameShop not found or could not be updated");
        }
    }

    @PutMapping("/shop/addGame")
    public ResponseEntity<String> addToGameList(@RequestParam Integer shopId,
                                                      @RequestParam Integer gameId){
        boolean updated = boardGameShopService.addToGameList(shopId, gameId);
        if (updated) {
            return ResponseEntity.ok().body("BoardGameShop updated successfully");
        } else {
            return ResponseEntity.status(404).body("BoardGameShop not found or could not be updated");
        }
    }

    @PutMapping("/shop/addRefreshement")
    public ResponseEntity<String> addToRefreshementList(@RequestParam Integer shopId,
                                                @RequestParam Integer refreshementId){
        boolean updated = boardGameShopService.addToRefreshmentList(shopId, refreshementId);
        if (updated) {
            return ResponseEntity.ok().body("BoardShop updated successfully");
        } else {
            return ResponseEntity.status(404).body("BoardShop not found or could not be updated");
        }
    }



}
