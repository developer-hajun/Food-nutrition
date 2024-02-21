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
public class Food {
    @Id
    @Column(name = "food_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; //이름
    private String manufacturer;//제조사
    private String type;//종류
    @Column(length = 1000)
    private String materials;

//    @OneToMany(mappedBy = "food")
//    private List<Materials> materialsList= new ArrayList<>();


    public Food(String name, String manufacturer, String type, String materials) {
        this.name = name;
        this.manufacturer = manufacturer;
        this.type = type;
        this.materials = materials;
    }
}
