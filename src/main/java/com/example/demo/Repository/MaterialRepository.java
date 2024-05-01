package com.example.demo.Repository;

import com.example.demo.Entity.Material;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MaterialRepository extends JpaRepository<Material,Long> {
    Optional<Material> findByName(String name);
}
