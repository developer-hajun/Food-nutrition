package com.example.demo.Repository;

import com.example.demo.Entity.Food;

import java.util.List;

public interface FoodRepositoryCustom {

    public List<Food> ingredient_not_included(String name);
}
