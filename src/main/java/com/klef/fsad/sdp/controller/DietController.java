package com.klef.fsad.sdp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.klef.fsad.sdp.entity.Diet;
import com.klef.fsad.sdp.service.DietService;

@RestController
@RequestMapping("/userapi/diets")
@CrossOrigin(origins = "http://localhost:3000") // if frontend runs on localhost:3000
public class DietController {

    @Autowired
    private DietService dietService;

    // Get all diets
    @GetMapping
    public List<Diet> getAllDiets() {
        return dietService.getAllDiets();
    }

    // Get diets for a specific user
    @GetMapping("/user/{userId}")
    public List<Diet> getUserDiets(@PathVariable int userId) {
        return dietService.getUserDiets(userId);
    }

    // Add a new diet
    @PostMapping
    public String addDiet(@RequestBody Diet diet) {
        return dietService.addDiet(diet);
    }

    // Update a diet
    @PutMapping("/{id}")
    public Diet updateDiet(@PathVariable int id, @RequestBody Diet diet) {
        diet.setId(id); // Make sure Diet entity has setId()
        return dietService.updateDiet(diet);
    }

    // Delete a diet
    @DeleteMapping("/{id}")
    public String deleteDiet(@PathVariable int id) {
        return dietService.deleteDiet(id);
    }
}