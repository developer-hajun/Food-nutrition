package com.example.demo.Controller;

import com.example.demo.Dto.FoodDto;
import com.example.demo.Entity.Food;
import com.example.demo.Entity.Material;
import com.example.demo.Entity.Member;
import com.example.demo.Repository.MaterialRepository;
import com.example.demo.Service.FoodService;
import com.example.demo.Service.FoodTypeService;
import com.example.demo.Service.MemberService;
import com.example.demo.Service.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class FoodController {
    private final MemberService memberService;
    private final FoodService foodService;
    private final TokenService tokenService;
    private final MaterialRepository materialRepository;
    private final FoodTypeService foodTypeService;
    @GetMapping("/memberfood")
    public List<FoodDto> findMemberEatFood(HttpServletRequest request){
        String token = request.getHeader("authorization").substring(7);
        String token_id = tokenService.GetTokenId(token);
        //토큰Repository에서 토큰에 있는 사용자의 id값 가져오기
        //MemberService에서 id값으로 사용자 찿기
        Member member = memberService.findByMemberID(token_id).get();
        List<Material> materialList = member.getMaterialList();
        //Member에 있는 Material값 가져오기
        List<String> food_name = materialList.stream().map(Material::getName).toList();
        //Material안에 있는 name값이 포함되어있지않은 식품 찾기
        List<Food> foods = foodService.Member_eat_food(food_name);
        List<FoodDto> list = foods.stream().map(f -> {
            return new FoodDto(f.getName());
        }).toList();
        return list;
    }

    public List<FoodDto> findManufacturer(String Manufacturer){
        //식품군의 이름을 가진 id를 ManufacturerService에서 가져오기
        //식품에서 해당 id를 가진 food들을 가져와서 보여준다
        return foodService.Find_Manufacturer(Manufacturer).stream().map(f->{
            return new FoodDto(f.getName());
        }).toList();
    }


    public List<FoodDto> findType(String Type_name){
        //식품군의 이름을 가진 id를 ManufacturerService에서 가져오기
        return foodService.Find_FoodType(Type_name).stream().map(f->{
            return new FoodDto(f.getName());
        }).toList();
    }

    @GetMapping("/add")
    public ResponseEntity<String> addMaterial(HttpServletRequest request, @RequestBody String name){
        String token = request.getHeader("authorization").substring(7);
        String token_id = tokenService.GetTokenId(token);
        Member member = memberService.findByMemberID(token_id).get();
        Material material = new Material(name);
        material.setMember(member);
        materialRepository.save(material);
        return ResponseEntity.ok().body("SUCCESS");
    }
}
