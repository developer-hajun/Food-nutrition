package com.example.demo.Controller;

import com.example.demo.Dto.MemberJoinDto;
import com.example.demo.Dto.MemberLoginDto;
import com.example.demo.Service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class LoginController {
    private final MemberService memberService;
    @PostMapping("/join")
    public ResponseEntity<String> join(@RequestBody MemberJoinDto dto){
        memberService.join(dto.getId(), dto.getPassword(),dto.getName());
        return ResponseEntity.ok().body("SUCCESS");
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody MemberLoginDto dto){
        String token = memberService.login(dto.getId(), dto.getPassword());
        return ResponseEntity.ok().body(token);
    }
}
