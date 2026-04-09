package com.klef.fsad.sdp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.klef.fsad.sdp.entity.Diet;
import com.klef.fsad.sdp.service.DietService;

@RestController
@RequestMapping("userapi")
@CrossOrigin("*")
public class DietController
{
    @Autowired
    private DietService dietService;

    @PostMapping("/addDiet")
    public ResponseEntity<String> addDiet(@RequestBody Diet diet)
    {
        try
        {
            String response = dietService.addDiet(diet);
            return ResponseEntity.status(201).body(response);
        }
        catch(Exception e)
        {
            return ResponseEntity.status(500).body("Failed to Add Diet");
        }
    }

    @GetMapping("getUserDiets/{userId}")
    public ResponseEntity<List<Diet>> getUserDiets(@PathVariable int userId)
    {
        return ResponseEntity.ok(dietService.getUserDiets(userId));
    }

    @PutMapping("updateDiet/{id}")
    public ResponseEntity<?> updateDiet(@PathVariable int id, @RequestBody Diet diet)
    {
        try
        {
            diet.setId(id);
            Diet updatedDiet = dietService.updateDiet(diet);
            return ResponseEntity.ok(updatedDiet);
        }
        catch(Exception e)
        {
            return ResponseEntity.status(500).body("Failed to Update Diet");
        }
    }

    @DeleteMapping("deleteDiet/{id}")
    public ResponseEntity<String> deleteDiet(@PathVariable int id)
    {
        try
        {
            String response = dietService.deleteDiet(id);
            return ResponseEntity.ok(response);
        }
        catch(Exception e)
        {
            return ResponseEntity.status(500).body("Failed to Delete Diet");
        }
    }
}