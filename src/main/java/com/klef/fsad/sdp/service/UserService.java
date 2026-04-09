package com.klef.fsad.sdp.service;

import com.klef.fsad.sdp.entity.User;

public interface UserService 
{
    public String addUser(User u);

    public User checkUserLogin(String email, String password);

	public User getUserById(int userId);

	public void deleteUser(int userId);

	
}