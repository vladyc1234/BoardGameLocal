package com.example.ProiectJava.service;

import com.example.ProiectJava.model.BoardGameShop;
import com.example.ProiectJava.model.Developer;
import com.example.ProiectJava.model.Developer;
import com.example.ProiectJava.repository.*;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class DeveloperService {

    private final DeveloperRepository developerRepository;

    public DeveloperService(DeveloperRepository developerRepository) {
        this.developerRepository = developerRepository;
    }

    public Developer saveDeveloper(Developer developer){
        return developerRepository.save(developer);
    }
    public boolean deleteDeveloper(int developerId) {
        boolean exists = developerRepository.existsById(developerId);
        if (!exists) {
            throw  new IllegalStateException("shop with Id " + developerId + " does not exist");
        }
        else {
            developerRepository.deleteById(developerId);
            return true;
        }

    }
    public List<Developer> getDevelopers(){
        List<Developer> developers = developerRepository.findAll();

        List<Developer> developersToReturn = new ArrayList<>();

        for (Developer value : developers){
            developersToReturn.add(value);
        }

        return developersToReturn;
    }

    @Transactional
    public boolean updateDeveloper(Integer developerId, Developer developerUpdate) {
        Developer developer = developerRepository.findById(developerId)
                .orElseThrow(() -> new IllegalStateException(
                        "Developer with id " + developerId + " doesn' t exist "
                ));
        if(developer != null)
        {
            if(developerUpdate.getLocation() != null && !developerUpdate.getLocation().isEmpty()){
                developer.setLocation(developerUpdate.getLocation());
            }

            if(developerUpdate.getTelephone() != null && !developerUpdate.getTelephone().isEmpty()){
                developer.setTelephone(developerUpdate.getTelephone());
            }

            if(developerUpdate.getDeveloperName() != null && !developerUpdate.getDeveloperName().isEmpty()){
                developer.setDeveloperName(developerUpdate.getDeveloperName());
            }

            return true;
        }
        else {
            return false;
        }

    }
}
