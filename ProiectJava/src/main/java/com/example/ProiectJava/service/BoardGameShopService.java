package com.example.ProiectJava.service;

import com.example.ProiectJava.model.*;
import com.example.ProiectJava.repository.*;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class BoardGameShopService {

    private final GameRepository gameRepository;
    private final BoardGameShopRepository boardGameShopRepository;
    private final RefreshmentRepository refreshmentRepository;

    public BoardGameShopService(GameRepository gameRepository, RefreshmentRepository refreshmentRepository, BoardGameShopRepository boardGameShopRepository) {
        this.gameRepository = gameRepository;
        this.refreshmentRepository = refreshmentRepository;
        this.boardGameShopRepository = boardGameShopRepository;
    }

    public BoardGameShop saveBoardGameShop(BoardGameShop boardGameShop){
        return boardGameShopRepository.save(boardGameShop);
    }

    public boolean deleteBoardGameShop(int shopId) {
        boolean exists = boardGameShopRepository.existsById(shopId);
        if (!exists) {
            throw  new IllegalStateException("shop with Id " + shopId + " does not exist");
        }
        else {
            boardGameShopRepository.deleteById(shopId);
            return true;
        }

    }

    public List<BoardGameShop> getBoardGameShops(){
        List<BoardGameShop> shops = boardGameShopRepository.findAll();

        List<BoardGameShop> shopsToReturn = new ArrayList<>();

        for (BoardGameShop value : shops){
            shopsToReturn.add(value);
        }

        return shopsToReturn;
    }
    @Transactional
    public List<Game> getBoardGameShopsGames(int shopId){
        BoardGameShop boardGameShop = boardGameShopRepository.findById(shopId)
                .orElseThrow(() -> new IllegalStateException(
                        "Shop with id " + shopId + " doesn' t exist "
                ));

        List<Game> games = boardGameShop.getGameList();

        List<Game> gamesToReturn = new ArrayList<>();

        for (Game value : games){
            gamesToReturn.add(value);
        }

        return gamesToReturn;
    }
    @Transactional
    public List<Refreshment> getBoardGameShopsRefreshments(int shopId){
        BoardGameShop boardGameShop = boardGameShopRepository.findById(shopId)
                .orElseThrow(() -> new IllegalStateException(
                        "Shop with id " + shopId + " doesn' t exist "
                ));

        List<Refreshment> refreshments = boardGameShop.getRefreshmentList();

        List<Refreshment> refreshmentsToReturn = new ArrayList<>();

        for (Refreshment value : refreshments){
            refreshmentsToReturn.add(value);
        }

        return refreshmentsToReturn;
    }

    @Transactional
    public boolean updateBoardGameShop(Integer shopId, BoardGameShop boardGameShopUpdate) {
        BoardGameShop boardGameShop = boardGameShopRepository.findById(shopId)
                .orElseThrow(() -> new IllegalStateException(
                        "Shop with id " + shopId + " doesn' t exist "
                ));
        if(boardGameShop != null)
        {
            if(boardGameShopUpdate.getLocation() != null && !boardGameShopUpdate.getLocation().isEmpty()){
                boardGameShop.setLocation(boardGameShopUpdate.getLocation());
            }

            if(boardGameShopUpdate.getTelephone() != null && !boardGameShopUpdate.getTelephone().isEmpty()){
                boardGameShop.setTelephone(boardGameShopUpdate.getTelephone());
            }

            if(boardGameShopUpdate.getClose_time() != null){
                boardGameShop.setClose_time(boardGameShopUpdate.getClose_time());
            }

            if(boardGameShopUpdate.getOpen_time() != null){
                boardGameShop.setOpen_time(boardGameShopUpdate.getOpen_time());
            }

            if(boardGameShopUpdate.getPlayer_fee() != null){
                boardGameShop.setPlayer_fee(boardGameShopUpdate.getPlayer_fee());
            }

            return true;
        }
        else {
            return false;
        }

    }

    @Transactional
    public boolean addToGameList(int shopId, int gameId) {
        BoardGameShop shop = boardGameShopRepository.findById(shopId)
                .orElseThrow(() -> new EntityNotFoundException("Shop not found with id: " + shopId));

        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new EntityNotFoundException("Game not found with id: " + gameId));

        if(shop != null && game != null) {
            shop.getGameList().add(game);
            game.getBoardGameShops().add(shop);
            return true;
        }
        else
        {
            return false;
        }
    }

    @Transactional
    public boolean addToRefreshmentList(int shopId, int refreshmentId) {
        BoardGameShop shop = boardGameShopRepository.findById(shopId)
                .orElseThrow(() -> new EntityNotFoundException("Shop not found with id: " + shopId));

        Refreshment refreshment = refreshmentRepository.findById(refreshmentId)
                .orElseThrow(() -> new EntityNotFoundException("Refreshment not found with id: " + refreshmentId));

        if(shop != null && refreshment != null) {
            shop.getRefreshmentList().add(refreshment);
            refreshment.getBoardGameShops().add(shop);
            return true;
        }
        else
        {
            return false;
        }
    }

//    @Transactional
//    public void saveSomeAlbumDetails(){
//        for(int i = 0 ; i < 10 ; i ++) {
//            AlbumDetails albumDetails = new AlbumDetails("Album details " + i);
//            albumDetailRepository.save(albumDetails);
//            if(i == 5) {
//                throw new RuntimeException("I = 5!!!!!");
//            }
//        }
//    }

}
