package com.klef.fsad.sdp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.klef.fsad.sdp.entity.Admin;
import com.klef.fsad.sdp.entity.User;
import com.klef.fsad.sdp.service.AdminService;
import com.klef.fsad.sdp.service.UserService;

@RestController
@RequestMapping("adminapi")
@CrossOrigin("*")
public class AdminController 
{
    @Autowired
    private UserService userService;

    @Autowired
    private AdminService AdminService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user)
    {
        try
        {
            String output = userService.addUser(user);
            return ResponseEntity.status(201).body(output);
        }
        catch(Exception e)
        {
            return ResponseEntity.status(500).body("Registration Failed");
        }
    }

  
    @PostMapping("/login")
    public ResponseEntity<?> checkAdminLogin(@RequestBody Admin admin) {
        Admin a = AdminService.verifyAdminLogin(admin.getUsername(), admin.getPassword());

        if (a != null) {
            return ResponseEntity.ok(a);
        } else {
            return ResponseEntity.status(401).body("Login Invalid");
        }
    }
}