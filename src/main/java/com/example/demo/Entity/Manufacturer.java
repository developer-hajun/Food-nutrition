package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Manufacturer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "manufacturer_no")
    private Long no;

    private String name;

    @OneToMany(mappedBy = "manufacturer")
    private List<Food> foodList = new ArrayList<>();
    public Manufacturer(String name) {
        this.name = name;
    }
}
