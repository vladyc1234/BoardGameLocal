package com.example.ProiectJava.controller;

import com.example.ProiectJava.model.BoardGameShop;
import com.example.ProiectJava.model.Developer;
import com.example.ProiectJava.service.DeveloperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/developers")
public class DeveloperServiceController {
    @Autowired
    private DeveloperService developerService;

    @PostMapping("/developer/new")
    public ResponseEntity<Developer> saveDeveloper(@RequestBody Developer developer){
        return ResponseEntity.ok().body(developerService.saveDeveloper(developer));
    }

    @DeleteMapping("/developer/delete")
    public ResponseEntity<String> deleteDeveloper(@RequestParam Integer developerId) {
        boolean deleted = developerService.deleteDeveloper(developerId);

        if (deleted) {
            return ResponseEntity.ok().body("Developer deleted successfully");
        } else {
            return ResponseEntity.status(404).body("Developer not found or could not be deleted");
        }
    }
    @GetMapping("/developer/get")
    public ResponseEntity<List<Developer>> getDevelopers(){
        return ResponseEntity.ok().body(developerService.getDevelopers());
    }

    @PutMapping("/developer/update")
    public ResponseEntity<String> updateDeveloper(@RequestParam Integer developerId,
                                                       @RequestBody Developer developer){
        boolean updated = developerService.updateDeveloper(developerId, developer);
        if (updated) {
            return ResponseEntity.ok().body("Developer updated successfully");
        } else {
            return ResponseEntity.status(404).body("Developer not found or could not be updated");
        }
    }
}
