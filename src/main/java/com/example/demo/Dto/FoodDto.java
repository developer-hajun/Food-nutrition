package com.example.demo.Dto;

import lombok.Data;

@Data
public class FoodDto {
    private String name; //이름
    public FoodDto(String name) {
        this.name = name;
    }
}
