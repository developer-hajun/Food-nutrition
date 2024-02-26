package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class FoodType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "food_type_no")
    private Long no;
    private String name;

    public FoodType(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "foodType")
    private List<Food> foodList = new ArrayList<>();
}
