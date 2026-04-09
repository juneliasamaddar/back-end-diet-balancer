package com.klef.fsad.sdp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.fsad.sdp.entity.User;
import com.klef.fsad.sdp.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService
{
    @Autowired
    private UserRepository userRepository;

    @Override
    public String addUser(User u) 
    {
        userRepository.save(u);
        return "User Registered Successfully";
    }

    @Override
    public User checkUserLogin(String email, String password) 
    {
        return userRepository.findByEmailAndPassword(email, password);
    }

	@Override
	public User getUserById(int userId) {
		    return userRepository.findById(userId).orElse(null);
	}
	
	@Override
	public void deleteUser(int userId) {
	    userRepository.deleteById(userId);
	}
}