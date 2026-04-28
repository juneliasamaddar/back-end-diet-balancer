package com.klef.fsad.sdp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.fsad.sdp.entity.Food;
import com.klef.fsad.sdp.repository.FoodRepository;

@Service
public class FoodService 
{
    @Autowired
    private FoodRepository repo;

    public Food getFoodByName(String name)
    {
        return repo.findByName(name);
    }

    public Food addFood(Food food)
    {
        return repo.save(food);
    }
}