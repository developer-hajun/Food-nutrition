package com.example.demo.Repository.FoodType;

import com.example.demo.Entity.Food;
import com.example.demo.Entity.FoodType;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.Assert;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class FoodTypeRepositoryTest {

    @Autowired FoodTypeRepository foodTypeRepository;
    @Autowired EntityManager em;
    @Test
    void findByName() {
        FoodType foodType = new FoodType("a");
        em.persist(foodType);
        em.clear();
        FoodType foodType1 = foodTypeRepository.findByName("a").get();
        assertEquals(foodType1.getName(),"a");
    }
}