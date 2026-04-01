package com.klef.fsad.sdp.service;

import com.klef.fsad.sdp.entity.User;

public interface UserService 
{
    public String addUser(User u);

    public User checkUserLogin(String email, String password);

	
}