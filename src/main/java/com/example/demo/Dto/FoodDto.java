package com.example.demo.Dto;

import lombok.Data;

@Data
public class FoodDto {
    private String name; //이름
    private String manufacturer;//제조사
    private String type;//종류

    public FoodDto(String name, String manufacturer, String type) {
        this.name = name;
        this.manufacturer = manufacturer;
        this.type = type;
    }
}
