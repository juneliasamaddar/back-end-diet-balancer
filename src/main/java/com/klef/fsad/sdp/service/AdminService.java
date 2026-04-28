package com.klef.fsad.sdp.service;

import java.util.List;

import com.klef.fsad.sdp.entity.Admin;
import com.klef.fsad.sdp.entity.Diet;
import com.klef.fsad.sdp.entity.User;

public interface AdminService 
{
    public Admin verifyAdminLogin(String username, String password);

    public List<User> viewAllUsers();

    public List<Diet> viewAllDiets();

    public String deleteUser(int id);

    public String deleteDiet(int id);
}