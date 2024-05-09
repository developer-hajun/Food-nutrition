package com.example.demo.Repository.Food;

import com.example.demo.Entity.Food;
import com.example.demo.Entity.FoodType;
import com.example.demo.Entity.Manufacturer;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class FoodRepositoryImplTest {
    @Autowired FoodRepository foodRepository;
    @Autowired
    EntityManager em;
    @Test
    void findByFoodType(){
        FoodType foodType = new FoodType();
        FoodType foodType1 = new FoodType();
        em.persist(foodType1);
        em.persist(foodType);
        Food food = new Food("A","A");
        Food food1 = new Food("B","B");
        Food food2 = new Food("C","C");
        Food food3 = new Food("D","D");
        Food food4 = new Food("E","E");
        food.setFoodType(foodType);
        food1.setFoodType(foodType1);
        food2.setFoodType(foodType);
        food3.setFoodType(foodType1);
        food4.setFoodType(foodType);
        em.persist(food);
        em.persist(food1);
        em.persist(food2);
        em.persist(food3);
        em.persist(food4);
        em.clear();
        Assert.assertEquals(foodRepository.findByFoodType(foodType).size(),3);
    }//테스트완료
    @Test
    void ingredient_not_included() {
        List<String> material = new ArrayList<>();
        material.add("간장");
        material.add("고추장");
        Food food = new Food("A","간장");
        Food food1 = new Food("B","고추장");
        Food food2 = new Food("C","후추");
        Food food3 = new Food("D","소금");
        em.persist(food);
        em.persist(food1);
        em.persist(food2);
        em.persist(food3);
        em.clear();
        List<Food> foods = foodRepository.ingredient_not_included(material);
        Assert.assertEquals(foods.size(),2);
    }

    @Test
    void findManufacturerFoods() {
        Manufacturer manufacturer = new Manufacturer("어묵");
        Manufacturer manufacturer2 = new Manufacturer("간장");
        em.persist(manufacturer);
        em.persist(manufacturer2);
        Food food = new Food("1번 어묵","");
        Food food0 = new Food("1번 간장","");
        Food food1 = new Food("2번 어묵","");
        Food food2 = new Food("2번 간장","");
        Food food3 = new Food("3번 어묵","");
        Food food4 = new Food("3번 간장","");
        food.setManufacturer(manufacturer);
        food0.setManufacturer(manufacturer2);
        food1.setManufacturer(manufacturer);
        food2.setManufacturer(manufacturer2);
        food3.setManufacturer(manufacturer);
        food4.setManufacturer(manufacturer2);
        em.persist(food);
        em.persist(food0);
        em.persist(food1);
        em.persist(food2);
        em.persist(food3);
        em.persist(food4);
        em.clear();
        List<Food> 어묵 = foodRepository.findManufacturerFoods("어묵");
        Assert.assertEquals(어묵.size(),3);
    }
}