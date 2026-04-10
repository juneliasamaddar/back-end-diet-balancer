package com.klef.fsad.sdp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.klef.fsad.sdp.entity.Admin;
import com.klef.fsad.sdp.entity.Diet;
import com.klef.fsad.sdp.entity.User;
import com.klef.fsad.sdp.service.AdminService;

@RestController
@RequestMapping("/adminapi")
@CrossOrigin("*")
public class AdminController 
{
    @Autowired
    private AdminService adminService;

    @PostMapping("/login")
    public ResponseEntity<?> checkAdminLogin(@RequestBody Admin admin)
    {
        try
        {
            Admin a = adminService.verifyAdminLogin(admin.getUsername(), admin.getPassword());

            if(a != null)
            {
                return ResponseEntity.status(200).body(a);
            }
            else
            {
                return ResponseEntity.status(401).body("Login Invalid");
            }
        }
        catch(Exception e)
        {
            return ResponseEntity.status(500).body("Internal Server Error");
        }
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> viewAllUsers()
    {
        return ResponseEntity.ok(adminService.viewAllUsers());
    }

    @GetMapping("/diets")
    public ResponseEntity<List<Diet>> viewAllDiets()
    {
        return ResponseEntity.ok(adminService.viewAllDiets());
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id)
    {
        return ResponseEntity.ok(adminService.deleteUser(id));
    }

    @DeleteMapping("/deleteDiet/{id}")
    public ResponseEntity<String> deleteDiet(@PathVariable int id)
    {
        return ResponseEntity.ok(adminService.deleteDiet(id));
    }
}