package com.example.demo.Dto;

import lombok.Data;

@Data
public class FoodDto {
    private String name; //이름
    private String manufacturer;//제조사
    public FoodDto(String name, String manufacturer) {
        this.name = name;
        this.manufacturer = manufacturer;
    }
}
