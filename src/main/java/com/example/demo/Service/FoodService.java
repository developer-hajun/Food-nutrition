package com.example.demo.Service;

import com.example.demo.Entity.Food;
import com.example.demo.Entity.FoodType;
import com.example.demo.Repository.Food.FoodRepository;
import com.example.demo.Repository.FoodType.FoodTypeRepository;
import com.example.demo.Repository.Manufacturer.ManufacturerRepository;
import com.example.demo.Repository.Memeber.MemberRepository;
import com.example.demo.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FoodService {
    private final MemberRepository memberRepository;
    @Value("${jwt.token.secret}")
    private String key;
    private final FoodRepository foodRepository;
    private final ManufacturerRepository manufacturerRepository;
    private final FoodTypeRepository foodTypeRepository;
    private final JwtTokenUtil jwtTokenUtil;
    public List<Food> MemberCanEat(String token){
        Long no = (Long) jwtTokenUtil.getclaims(token).get("no");
        List<String> memberMaterial = memberRepository.findMemberMaterial(no);
        return foodRepository.ingredient_not_included(memberMaterial);
    }//사용자가 자신이 못먹는것들을 등록해놓은 성분이 뺴고 들어가있는걸 찾기

    public List<Food> Find_Manufacturer(String name){
        return foodRepository.findManufacturerFoods(name);
    }
    //제조사별 식품조회
    public List<Food> Member_eat_food(List<String> food_name){
        return foodRepository.ingredient_not_included(food_name);
    }
    public List<Food> Find_FoodType(String name){
        Optional<FoodType> byName = foodTypeRepository.findByName(name);
        FoodType foodType = byName.get();
        return foodRepository.findByFoodType(foodType);
    }
    //식품군별 식품 조회

}
