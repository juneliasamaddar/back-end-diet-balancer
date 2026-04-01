package com.klef.fsad.sdp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.klef.fsad.sdp.entity.Admin;
import com.klef.fsad.sdp.service.AdminService;

@RestController
@RequestMapping("adminapi")
@CrossOrigin("*")
public class AdminController 
{
    @Autowired
    private AdminService adminService;

    @PostMapping("/register")
    public ResponseEntity<String> registerAdmin(@RequestBody Admin admin)
    {
        try
        {
            String output = adminService.addAdmin(admin);
            return ResponseEntity.status(201).body(output);
        }
        catch(Exception e)
        {
            return ResponseEntity.status(500).body("Admin Registration Failed");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> checkAdminLogin(@RequestBody Admin admin) 
    {
        Admin a = adminService.verifyAdminLogin(admin.getUsername(), admin.getPassword());

        if (a != null) 
        {
            return ResponseEntity.ok(a);
        } 
        else 
        {
            return ResponseEntity.status(401).body("Login Invalid");
        }
    }
}