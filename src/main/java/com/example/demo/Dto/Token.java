package com.example.demo.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;

@Getter
@AllArgsConstructor
public class Token {
    private String AccessToken;
    private String ReFreshToken;
    private String key;
}
