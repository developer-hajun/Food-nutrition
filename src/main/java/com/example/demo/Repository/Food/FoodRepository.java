package com.example.demo.Repository.Food;

import com.example.demo.Entity.Food;
import com.example.demo.Entity.FoodType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodRepository extends JpaRepository<Food, Long> , FoodRepositoryCustom{
    List<Food> findByFoodType(FoodType foodType);
}
