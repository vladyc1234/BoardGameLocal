package com.example.ProiectJava.service;

import com.example.ProiectJava.model.Game;
import com.example.ProiectJava.model.Refreshment;
import com.example.ProiectJava.repository.BoardGameShopRepository;
import com.example.ProiectJava.repository.EventRepository;
import com.example.ProiectJava.repository.GameRepository;
import com.example.ProiectJava.repository.RefreshmentRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class RefreshmentService {
    private final RefreshmentRepository refreshmentRepository;

    public RefreshmentService(RefreshmentRepository refreshmentRepository) {
        this.refreshmentRepository = refreshmentRepository;
    }

    public Refreshment saveRefreshment(Refreshment refreshment){
        return refreshmentRepository.save(refreshment);
    }

    public boolean deleteRefreshment(int refreshmentId) {
        boolean exists = refreshmentRepository.existsById(refreshmentId);
        if (!exists) {
            throw  new IllegalStateException("shop with Id " + refreshmentId + " does not exist");
        }
        else {
            refreshmentRepository.deleteById(refreshmentId);
            return true;
        }

    }

    public List<Refreshment> getRefreshments(){
        List<Refreshment> refreshments = refreshmentRepository.findAll();

        List<Refreshment> refreshmentsToReturn = new ArrayList<>();

        for (Refreshment value : refreshments){
            refreshmentsToReturn.add(value);
        }

        return refreshmentsToReturn;
    }

    @Transactional
    public boolean updateRefreshment(Integer refreshmentId, Refreshment refreshmentUpdate) {
        Refreshment refreshment = refreshmentRepository.findById(refreshmentId)
                .orElseThrow(() -> new IllegalStateException(
                        "Refreshment with id " + refreshmentId + " doesn' t exist "
                ));
        if(refreshment != null)
        {
            if(refreshmentUpdate.getName() != null && !refreshmentUpdate.getName().isEmpty()){
                refreshment.setName(refreshmentUpdate.getName());
            }

            if(refreshmentUpdate.getPrice() != null){
                refreshment.setPrice(refreshmentUpdate.getPrice());
            }

            return true;
        }
        else {
            return false;
        }

    }
}
