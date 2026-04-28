package com.klef.fsad.sdp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.fsad.sdp.entity.Admin;
import com.klef.fsad.sdp.entity.Diet;
import com.klef.fsad.sdp.entity.User;
import com.klef.fsad.sdp.repository.AdminRepository;
import com.klef.fsad.sdp.repository.DietRepository;
import com.klef.fsad.sdp.repository.UserRepository;

@Service
public class AdminServiceImpl implements AdminService
{
    @Autowired
    private AdminRepository adminRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private DietRepository dietRepo;

    @Override
    public Admin verifyAdminLogin(String username, String password)
    {
        return adminRepo.findByUsernameAndPassword(username.trim(), password.trim());
    }

    @Override
    public List<User> viewAllUsers()
    {
        return userRepo.findAll();
    }

    @Override
    public List<Diet> viewAllDiets()
    {
        return dietRepo.findAll();
    }

    @Override
    public String deleteUser(int id)
    {
        userRepo.deleteById(id);
        return "User Deleted Successfully";
    }

    @Override
    public String deleteDiet(int id)
    {
        dietRepo.deleteById(id);
        return "Diet Deleted Successfully";
    }
}