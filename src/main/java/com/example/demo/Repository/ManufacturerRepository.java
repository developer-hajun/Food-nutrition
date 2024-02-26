package com.example.demo.Repository;

import com.example.demo.Entity.FoodType;
import com.example.demo.Entity.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ManufacturerRepository extends JpaRepository<Manufacturer,Long> {
    Optional<Manufacturer> findByName(String name);
}
