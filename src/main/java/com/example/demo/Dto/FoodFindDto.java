package com.example.demo.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@AllArgsConstructor
@Getter
public class FoodFindDto {
    private List<String> names;
    private int page;

}