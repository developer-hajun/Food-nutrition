package com.example.demo.Repository.Food;

import com.example.demo.Entity.Food;

import java.util.List;

public interface FoodRepositoryCustom {

    public List<Food> ingredient_not_included(List<String> name);
    List<Food> findManufacturerFoods(String name);

}
