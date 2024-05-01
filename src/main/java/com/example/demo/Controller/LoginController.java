package com.example.demo.Controller;

import com.example.demo.Dto.MemberJoinDto;
import com.example.demo.Dto.MemberLoginDto;
import com.example.demo.Dto.RefreshTokenDto;
import com.example.demo.Dto.Token;
import com.example.demo.Service.MemberService;
import com.example.demo.Service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LoginController {
    private final MemberService memberService;
    private final TokenService refreshTokenService;
    @PostMapping("/join")
    public ResponseEntity<String> join(@RequestBody MemberJoinDto dto){
        memberService.join(dto.getId(), dto.getPassword(),dto.getName());
        return ResponseEntity.ok().body("SUCCESS");
    }
    @PostMapping("/login")
    public ResponseEntity<Token> login(@RequestBody MemberLoginDto dto){
        List<String> tokens = memberService.login(dto.getId(), dto.getPassword());
        Token token = new Token(tokens.get(0), tokens.get(1),dto.getId());
        refreshTokenService.save(tokens.get(1));
        return ResponseEntity.ok().body(token);
    }

    @PostMapping("/logout")
    public ResponseEntity logout(@RequestBody RefreshTokenDto refreshToken){
        refreshTokenService.deleteToken(refreshToken);
        return new ResponseEntity(HttpStatus.OK);
    }
}
