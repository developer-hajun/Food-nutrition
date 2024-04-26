package com.example.demo.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Token {
    private String AccessToken;
    private String ReFreshToken;
    private String key;
}
