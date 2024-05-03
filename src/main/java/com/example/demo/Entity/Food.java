package com.example.demo.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Food {
    @Id
    @Column(name = "food_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; //이름

    @Column(length = 1000)
    private String materials;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_type_no")
    @JsonIgnore
    private FoodType foodType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manufacturer_no")
    @JsonIgnore
    private Manufacturer manufacturer;

    public void setFoodType(FoodType foodType){
        this.foodType = foodType;
        foodType.getFoodList().add(this);
    }
    public void setManufacturer(Manufacturer manufacturer){
        this.manufacturer = manufacturer;
        manufacturer.getFoodList().add(this);
    }
    public Food(String name,String materials) {
        this.name = name;
        this.materials = materials;
    }
}
