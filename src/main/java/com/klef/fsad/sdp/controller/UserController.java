package com.klef.fsad.sdp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.klef.fsad.sdp.entity.User;
import com.klef.fsad.sdp.service.UserService;

@RestController
@RequestMapping("userapi")
@CrossOrigin("*")
public class UserController 
{
    @Autowired
    private UserService userService;

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
        User u = userService.checkUserLogin(user.getEmail(), user.getPassword());

        if(u != null) {
            return ResponseEntity.status(200).body(u);
        } else {
            return ResponseEntity.status(401).body("Login Invalid");
        }
    }
}