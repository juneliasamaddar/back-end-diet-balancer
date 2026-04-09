package com.klef.fsad.sdp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.klef.fsad.sdp.entity.Diet;

public interface DietRepository extends JpaRepository<Diet, Integer>
{
    List<Diet> findByUserId(int userId);
}