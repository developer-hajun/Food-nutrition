package com.example.demo.Repository;

import com.example.demo.Entity.FoodType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FoodTypeRepository extends JpaRepository<FoodType,Long> {
    Optional<FoodType> findByName(String name);
}
