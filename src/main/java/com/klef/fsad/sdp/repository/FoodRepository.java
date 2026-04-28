package com.klef.fsad.sdp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.klef.fsad.sdp.entity.Food;

public interface FoodRepository extends JpaRepository<Food, Integer>
{
    public Food findByName(String name);
}