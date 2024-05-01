package com.example.demo.Service;

import com.example.demo.Dto.FoodDto;
import com.example.demo.Entity.Food;
import com.example.demo.Entity.FoodType;
import com.example.demo.Repository.Food.FoodRepository;
import com.example.demo.Repository.FoodType.FoodTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FoodTypeService {
    private final FoodTypeRepository foodTypeRepository;
    private final FoodRepository foodRepository;
    public List<FoodDto> foodTypefind(FoodType foodType){
        List<Food> byFoodType = foodRepository.findByFoodType(foodType);
        return byFoodType.stream().map(f -> {
            return new FoodDto(f.getName());
        }).toList();
    }
    public FoodType findByName(String name){
        return foodTypeRepository.findByName(name).get();
    }
}

