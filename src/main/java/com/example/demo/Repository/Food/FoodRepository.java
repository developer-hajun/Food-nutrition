package com.example.demo.Repository.Food;

import com.example.demo.Entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, Long> , FoodRepositoryCustom{
}
