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
    private AdminRepository adminRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DietRepository dietRepository;

    @Override
    public Admin verifyAdminLogin(String username, String password) 
    {
        return adminRepository.findByUsernameAndPassword(username, password);
    }

    @Override
    public List<User> viewAllUsers() 
    {
        return userRepository.findAll();
    }

    @Override
    public List<Diet> viewAllDiets() 
    {
        return dietRepository.findAll();
    }

    @Override
    public String deleteUser(int id) 
    {
        userRepository.deleteById(id);
        return "User Deleted Successfully";
    }

    @Override
    public String deleteDiet(int id) 
    {
        dietRepository.deleteById(id);
        return "Diet Deleted Successfully";
    }
}