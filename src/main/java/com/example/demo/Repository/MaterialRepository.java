package com.example.demo.Repository;

import com.example.demo.Entity.Materials;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaterialRepository extends JpaRepository<Materials, Long> {

}