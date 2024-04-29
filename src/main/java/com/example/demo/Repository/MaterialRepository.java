package com.example.demo.Repository;

import com.example.demo.Entity.Material;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaterialRepository extends JpaRepository<Material,Long> {
}
