package com.example.demo.Controller;

import com.example.demo.Dto.MemberJoinDto;
import com.example.demo.Entity.Material;
import com.example.demo.Entity.Member;
import com.example.demo.Repository.MaterialRepository;
import com.example.demo.Repository.Memeber.MemberRepository;
import com.example.demo.Service.MemberService;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Random;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Arrays;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class randomData {
    private final MaterialRepository materialRepository;
    private final MemberRepository memberRepository;
    @PostMapping("/createMember")
    public ResponseEntity<String> join2(){
        LocalDateTime now = LocalDateTime.now();
        List<String> food_name = Arrays.asList("냉동연육","전분", "밀가루", "정제염", "소르빈산칼륨", "글루코노-δ-락톤", "L-글루타민산나트륨", "설탕","정제수", "타피오카전분", "가시오갈피분말","간장", "고춧가루", "마늘", "참기름", "생강", "들깨", "참깨");
        for(int i=0;i<100;i++){
            Member member = new Member("user"+Integer.toString(i),Integer.toString(i),"user"+Integer.toString(i));
            memberRepository.save(member);
        }
        LocalDateTime last = LocalDateTime.now();
        Duration diff = Duration.between(now, last);
        System.out.println(diff);
        return ResponseEntity.ok().body("생성완료");

    }
    @PostMapping("/createMaterial")
    public ResponseEntity<String> join3(){
        List<String> food_name = Arrays.asList("냉동연육","전분", "밀가루", "정제염", "소르빈산칼륨", "글루코노-δ-락톤", "L-글루타민산나트륨", "설탕","정제수", "타피오카전분", "가시오갈피분말","간장", "고춧가루", "마늘", "참기름", "생강", "들깨", "참깨");
        for(int i=1;i<101;i++){
            Long q = Long.valueOf(i);
            Member member = memberRepository.findById(q).get();
            Random random = new Random();
            int randomIndex = random.nextInt(food_name.size());
            Material material = new Material(food_name.get(randomIndex));
            material.setMember(member);
            randomIndex = random.nextInt(food_name.size());
            Material material2 = new Material(food_name.get(randomIndex));
            material2.setMember(member);
            randomIndex = random.nextInt(food_name.size());
            Material material3 = new Material(food_name.get(randomIndex));
            material3.setMember(member);
            materialRepository.save(material);
            materialRepository.save(material2);
            materialRepository.save(material3);
        }

        return ResponseEntity.ok().body("생성완료");
    }
}
