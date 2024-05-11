package com.example.demo.Repository.token;


import com.example.demo.Entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken,Long> {
    boolean existsByToken(String token);

    Optional<RefreshToken> findByToken(String token);
}
