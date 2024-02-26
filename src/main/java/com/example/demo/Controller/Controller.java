package com.example.demo.Controller;

import com.example.demo.Dto.FoodDto;
import com.example.demo.OpenApiManager;
import com.example.demo.Repository.FoodRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;


@RestController
@RequiredArgsConstructor
@Slf4j
public class Controller {
    private final OpenApiManager openApiManager;
    private final FoodRepository foodRepository;

    @GetMapping("openApi")
    public void setUp() throws ParseException {
        int start = 1;
        for(int i = 1;i<=10;i++){
            openApiManager.fetch(start,start+999);
            start += 1000;
        }
    }

    @GetMapping("find")
    public List<FoodDto> fetch(@RequestParam List<String> name, int page) throws IOException, ParseException {
        List<FoodDto> right = foodRepository.ingredient_not_included(name).stream().map(f -> {
            return new FoodDto(f.getName(), f.getType(), f.getManufacturer());
        }).toList();
        int p;
        if(page!=0) p = page*10;
        else p = 0;
        List<FoodDto> list = right.stream().skip(p).limit(p+10).toList();
        return list;
    }
}
