package com.klef.fsad.sdp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.klef.fsad.sdp.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>
{
    User findByEmailAndPassword(String email, String password);
    
    
}