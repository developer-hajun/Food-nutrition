package com.example.demo.Controller;

import com.example.demo.Dto.FoodDto;
import com.example.demo.Dto.FoodFindDto;
import com.example.demo.util.OpenApiManager;
import com.example.demo.Repository.Food.FoodRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


@RestController
@RequiredArgsConstructor
@Slf4j
public class ApiController {
    private final OpenApiManager openApiManager;
    private final FoodRepository foodRepository;

    @GetMapping("/openApi")
    public void setUp() throws ParseException {
        int start = 1;
        for(int i = 1;i<=10;i++){
            openApiManager.fetch(start,start+999);
            start += 1000;
        }
    }

    @PostMapping("/find")
    public List<FoodDto> fetch(@RequestBody FoodFindDto foodFindDto) throws IOException, ParseException {
        List<String> name = foodFindDto.getNames();
        int page = foodFindDto.getPage();
        List<FoodDto> right = foodRepository.ingredient_not_included(name).stream().map(f -> {
            return new FoodDto(f.getName());
        }).toList();
        int p;
        if(page!=0) p = page*10;
        else p = 0;
        List<FoodDto> list = right.stream().skip(p).limit(p+10).toList();
        return list;
    }//사용자가 체크한 검색창에 체크한 것들을 뺴고 검색
}
