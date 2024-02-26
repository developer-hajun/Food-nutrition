package com.example.demo.util;

import com.example.demo.Entity.Food;
import com.example.demo.Entity.FoodType;
import com.example.demo.Exception.AppException;
import com.example.demo.Exception.ErrorCode;
import com.example.demo.Repository.Food.FoodRepository;
import com.example.demo.Repository.FoodTypeRepository;
import com.example.demo.Repository.MaterialsRepository;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class OpenApiManager {
    private final String BASE_URL = "https://openapi.foodsafetykorea.go.kr/api/9324483c1f8d49f28cbf/C002/json";
//    private final String pageNum = "/1;
//    private final String pageCount = "/100";
    private final FoodRepository foodRepository;
    private final FoodTypeRepository foodTypeRepository;
    private final MaterialsRepository materialsRepository;

    private String makeUrl(int a , int b) {
        return BASE_URL+"/"+a+"/"+b;
    }

    public void fetch(int a,int b) throws ParseException {
        RestTemplate restTemplate = new RestTemplate();
        String jsonString = restTemplate.getForObject(makeUrl(a,b), String.class);
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(jsonString);
        JSONObject jsonC002 = (JSONObject) jsonObject.get("C002");
        JSONArray jsonRow = (JSONArray) jsonC002.get("row");
        for (Object o : jsonRow) {
           makeFood((JSONObject) o);
        }
    }

    private void makeFood(JSONObject food){
        Food makeFood  = new Food(
                food.get("PRDLST_NM").toString(),
                food.get("BSSH_NM").toString(),
//                food.get("PRDLST_DCNM").toString(),
                food.get("RAWMTRL_NM").toString()
        );
        foodRepository.save(makeFood);
        String food_type = food.get("PRDLST_DCNM").toString();
        Optional<FoodType> byName = foodTypeRepository.findByName(food_type);
        if(byName.isEmpty()){
            FoodType foodType = new FoodType(food_type);
            foodTypeRepository.save(foodType);
            makeFood.setFoodType(foodType);
        }
        else{
            makeFood.setFoodType(byName.get());
        }
    }
}
