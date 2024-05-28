package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("refreshToken")
@Data
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="token_no")
    private Long id;
    private String token;
    private Long userNo;

    public RefreshToken(String token, Long userNo) {
        this.token = token;
        this.userNo = userNo;
    }

    public RefreshToken() {

    }
}
