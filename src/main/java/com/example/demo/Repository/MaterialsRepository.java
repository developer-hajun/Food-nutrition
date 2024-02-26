package com.example.demo.Repository;

import com.example.demo.Entity.Food;
import com.example.demo.Entity.Materials;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaterialsRepository extends JpaRepository<Materials, Long>{
}
