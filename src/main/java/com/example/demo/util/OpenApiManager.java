package com.example.demo.util;

import com.example.demo.Entity.Food;
import com.example.demo.Entity.FoodType;
import com.example.demo.Entity.Manufacturer;
import com.example.demo.Repository.Food.FoodRepository;
import com.example.demo.Repository.FoodType.FoodTypeRepository;
import com.example.demo.Repository.Manufacturer.ManufacturerRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util. Optional;

@Component
@RequiredArgsConstructor
public class OpenApiManager {
    private final String BASE_URL = "https://openapi.foodsafetykorea.go.kr/api/9324483c1f8d49f28cbf/C002/json";
    private final FoodRepository foodRepository;
    private final ManufacturerRepository manufacturerRepository;
    private final FoodTypeRepository foodTypeRepository;

    private String makeUrl(int a , int b) {
        return BASE_URL+"/"+a+"/"+b;
    }

    public void fetch(int a,int b) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        String jsonString = restTemplate.getForObject(makeUrl(a,b), String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode json = objectMapper.readTree(jsonString);
        JsonNode C002 = json.get("C002");
        JsonNode row = C002.get("row");

        if(row.isArray()){
            JsonNode[] rowArray = new JsonNode[row.size()];
            for (int i = 0; i < row.size(); i++) {
                rowArray[i] = row.get(i);
            }
            for (JsonNode jsonNode : rowArray) {
                makeFood(jsonNode);
            }
        }
    }

    private void makeFood(JsonNode food){
        Food makeFood  = new Food(
                food.get("PRDLST_NM").asText(),
                food.get("RAWMTRL_NM").asText()
        );
        foodRepository.save(makeFood);
        String food_type = food.get("PRDLST_DCNM").asText();
        Optional<FoodType> type = foodTypeRepository.findByName(food_type);
        String manufacturer = food.get("BSSH_NM").asText();
        Optional<Manufacturer> made = manufacturerRepository.findByName(manufacturer);
        if(type.isEmpty()){
            FoodType foodType = new FoodType(food_type);
            foodTypeRepository.save(foodType);
            makeFood.setFoodType(foodType);
        }
        else{
            makeFood.setFoodType(type.get());
        }
        if(made.isEmpty()){
            Manufacturer manufacturer1 = new Manufacturer(manufacturer);
            manufacturerRepository.save(manufacturer1);
            makeFood.setManufacturer(manufacturer1);
        }
        else{
            makeFood.setManufacturer(made.get());
        }
    }
}
