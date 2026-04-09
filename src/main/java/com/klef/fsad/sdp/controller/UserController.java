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
        return "Diet Balancer Backend Running Successfully";
    }

    @PostMapping("/registerUser")
    public ResponseEntity<String> registerUser(@RequestBody User user)
    {
        try
        {
            String response = userService.addUser(user);
            return ResponseEntity.status(201).body(response);
        }
        catch(Exception e)
        {
            return ResponseEntity.status(500).body("Registration Failed");
        }
    }

    @PostMapping("/loginUser")
    public ResponseEntity<?> loginUser(@RequestBody User user)
    {
        User u = userService.checkUserLogin(user.getEmail(), user.getPassword());

        if(u != null)
        {
            return ResponseEntity.ok(u);
        }
        else
        {
            return ResponseEntity.status(401).body("Login Invalid");
        }
    }

    @GetMapping("getUser/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable int userId)
    {
        User user = userService.getUserById(userId);

        if(user != null)
        {
            return ResponseEntity.ok(user);
        }
        else
        {
            return ResponseEntity.status(404).body("User Not Found");
        }
    }
}