package com.example.demo.Repository.token;


import com.example.demo.Entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<RefreshToken,Long> {
    boolean existsByToken(String token);

    RefreshToken findByToken(String token);
}
