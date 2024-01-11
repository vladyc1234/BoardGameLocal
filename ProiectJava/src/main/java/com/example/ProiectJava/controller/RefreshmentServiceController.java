package com.example.ProiectJava.controller;

import com.example.ProiectJava.model.Game;
import com.example.ProiectJava.model.Refreshment;
import com.example.ProiectJava.service.BoardGameShopService;
import com.example.ProiectJava.service.RefreshmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/refreshments")
public class RefreshmentServiceController {

    @Autowired
    private RefreshmentService refreshmentService;
    @PostMapping("/refreshment/new")
    public ResponseEntity<Refreshment> saveRefreshment(@RequestBody Refreshment refreshment){
        return ResponseEntity.ok().body(refreshmentService.saveRefreshment(refreshment));
    }
    @DeleteMapping("/refreshment/delete")
    public ResponseEntity<String> deleteRefreshment(@RequestParam Integer refreshmentId) {
        boolean deleted = refreshmentService.deleteRefreshment(refreshmentId);

        if (deleted) {
            return ResponseEntity.ok().body("Refreshment deleted successfully");
        } else {
            return ResponseEntity.status(404).body("Refreshment not found or could not be deleted");
        }
    }
    @GetMapping("/refreshment/get")
    public ResponseEntity<List<Refreshment>> getRefreshments(){
        return ResponseEntity.ok().body(refreshmentService.getRefreshments());
    }

    @PutMapping("/refreshment/update")
    public ResponseEntity<String> updateRefreshment(@RequestParam Integer refreshmentId,
                                             @RequestBody Refreshment refreshment){
        boolean updated = refreshmentService.updateRefreshment(refreshmentId, refreshment);
        if (updated) {
            return ResponseEntity.ok().body("Refreshment updated successfully");
        } else {
            return ResponseEntity.status(404).body("Refreshment not found or could not be updated");
        }
    }
}
