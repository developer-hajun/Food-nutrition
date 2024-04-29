package com.example.demo.Controller;

import com.example.demo.Dto.FoodDto;
import com.example.demo.Service.FoodService;
import com.example.demo.Service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class FoodController {
    private final MemberService memberService;
    private final FoodService foodService;

    @GetMapping("/member_food")
    public void findMemberEatFood(){
        //토큰Repository에서 토큰에 있는 사용자의 id값 가져오기
        //MemberService에서 id값으로 사용자 찿기
        //Member에 있는 Material값 가져오기
        //Material안에 있는 name값이 포함되어있지않은 식품 찾기
    }

    public void findManufacturer(){
        //식품군의 이름을 가진 id를 ManufacturerService에서 가져오기
        //식품에서 해당 id를 가진 food들을 가져와서 보여준다
    }


    public void findType(){
        //식품군의 이름을 가진 id를 ManufacturerService에서 가져오기
        //식품에서 해당 id를 가진 food들을 가져와서 보여준다
    }
}
