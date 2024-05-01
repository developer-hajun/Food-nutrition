package com.example.demo.Repository.token;


import com.example.demo.Entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken,Long> {
    boolean existsByToken(String token);

    Optional<RefreshToken> findByToken(String token);
}
