package com.klef.fsad.sdp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;

@Entity
public class Diet 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String mealType;
    private String description;
    private int userId;
    
    // Nutrients
    private int protein;
    private int carbs;
    private int fats;
    private int calories;

    // Date
    private LocalDate date;

    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getMealType() { return mealType; }
    public void setMealType(String mealType) { this.mealType = mealType; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public int getProtein() { return protein; }
    public void setProtein(int protein) { this.protein = protein; }

    public int getCarbs() { return carbs; }
    public void setCarbs(int carbs) { this.carbs = carbs; }

    public int getFats() { return fats; }
    public void setFats(int fats) { this.fats = fats; }

    public int getCalories() { return calories; }
    public void setCalories(int calories) { this.calories = calories; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    @Override
    public String toString() {
        return "Diet [id=" + id + ", mealType=" + mealType + ", description=" + description 
               + ", userId=" + userId + ", protein=" + protein + ", carbs=" + carbs 
               + ", fats=" + fats + ", calories=" + calories + ", date=" + date + "]";
    }
}