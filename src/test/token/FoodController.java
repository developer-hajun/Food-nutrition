package com.example.demo.Controller;

import com.example.demo.Dto.FoodDto;
import com.example.demo.Entity.Food;
import com.example.demo.Entity.Material;
import com.example.demo.Entity.Member;
import com.example.demo.Repository.MaterialRepository;
import com.example.demo.Service.FoodService;
import com.example.demo.Service.FoodTypeService;
import com.example.demo.Service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class FoodController {
    private final MemberService memberService;
    private final FoodService foodService;
    private final MaterialRepository materialRepository;
    private final FoodTypeService foodTypeService;
    @GetMapping("/member_food")
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

    @GetMapping("/manufacturer")
    public List<Food> findManufacturer(@RequestParam String Manufacturer, Principal principal){
        //식품군의 이름을 가진 id를 ManufacturerService에서 가져오기
        //식품에서 해당 id를 가진 food들을 가져와서 보여준다
        System.out.println(principal.getName()+"a");
        return foodService.Find_Manufacturer(Manufacturer);
    }


    @GetMapping("/hajun")
    public List<FoodDto> findType(@RequestParam String Type_name){
        //식품군의 이름을 가진 id를 ManufacturerService에서 가져오기
        return foodService.Find_FoodType(Type_name).stream().map(f->{
            return new FoodDto(f.getName());
        }).toList();
    }

    @GetMapping("/add")
    public ResponseEntity<String> addMaterial(HttpServletRequest request, @RequestParam String name){
        String token = request.getHeader("authorization").substring(7);
        String token_id = tokenService.GetTokenId(token);
        Optional<Member> optionalmember = memberService.findByMemberID(token_id);

        if(optionalmember.isPresent()) {
            Material material = new Material(name);
            material.setMember(optionalmember.get());
            materialRepository.save(material);
            return ResponseEntity.ok().body("SUCCESS");
        }
        return ResponseEntity.ok().body("false");
    }
}
