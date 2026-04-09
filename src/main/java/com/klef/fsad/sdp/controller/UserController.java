package com.klef.fsad.sdp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.klef.fsad.sdp.entity.Diet;
import com.klef.fsad.sdp.entity.User;
import com.klef.fsad.sdp.service.DietService;
import com.klef.fsad.sdp.service.UserService;

@RestController
@RequestMapping("userapi")
@CrossOrigin("*")
public class UserController 
{
    @Autowired
    private UserService userService;
    
    @Autowired
    private DietService dietService;

    @GetMapping("/")
    public String index()
    {
        return "Sdp project is running";
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User u)
    {
        try
        {
            String output = userService.addUser(u);
            return ResponseEntity.status(201).body(output);
        }
        catch(Exception e)
        {
            return ResponseEntity.status(500).body("Registration Failed");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> checkUserLogin(@RequestBody User user) {
        User u=userService.checkUserLogin(user.getEmail(), user.getPassword());

        if(u!=null) {
            return ResponseEntity.status(200).body(u);
        } else {
            return ResponseEntity.status(401).body("Login Invalid");
        }
    }
    
    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable int userId) {
        User u=userService.getUserById(userId);
        if(u!=null) {
            return ResponseEntity.ok(u);
        } else {
            return ResponseEntity.status(404).body("User Not Found");
        }
    }
    
    @PostMapping("/diet")
    public String addDiet(@RequestBody Diet diet) {
        return dietService.addDiet(diet);
    }
    
    @GetMapping("/diet/{userId}")
    public List<Diet> getDiets(@PathVariable int userId) {
        return dietService.getUserDiets(userId);
    }
    
    @PutMapping("/diet/{id}")
    public ResponseEntity<?> updateDiet(@PathVariable int id, @RequestBody Diet diet) {
        diet.setId(id); // assign the ID
        Diet updated = dietService.updateDiet(diet);

        if(updated != null)
            return ResponseEntity.ok(updated);
        else
            return ResponseEntity.status(404).body("Diet Not Found");
    }
    
    @DeleteMapping("/diet/{id}")
    public ResponseEntity<String> deleteDiet(@PathVariable int id){
        dietService.deleteDiet(id);
        return ResponseEntity.ok("Diet deleted successfully");
    }
}