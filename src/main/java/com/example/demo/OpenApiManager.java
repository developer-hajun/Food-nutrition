package com.example.demo;

import com.example.demo.Entity.Food;
import com.example.demo.Entity.Materials;
import com.example.demo.Repository.FoodRepository;
import com.example.demo.Repository.MaterialRepository;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class OpenApiManager {
    private final String BASE_URL = "https://openapi.foodsafetykorea.go.kr/api/9324483c1f8d49f28cbf/C002/json";
    private final String pageNum = "/1";
    private final String pageCount = "/1000";
    private final FoodRepository foodController;
    private final MaterialRepository materialsController;

    private String makeUrl() {
        return BASE_URL+pageNum+pageCount;
    }

    public void fetch() throws ParseException {
        RestTemplate restTemplate = new RestTemplate();
        String jsonString = restTemplate.getForObject(makeUrl(), String.class);
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
                food.get("PRDLST_DCNM").toString()
        );
        foodController.save(makeFood);
        String materials = food.get("RAWMTRL_NM").toString();
        String[] list = materials.split(",");
        for (String s : list) {
            Materials material = new Materials(s);
            material.setFood(makeFood);
            materialsController.save(material);
        }
    }
}
