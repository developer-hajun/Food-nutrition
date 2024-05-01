package com.example.demo.Service;

import com.example.demo.Dto.RefreshTokenDto;
import com.example.demo.Entity.RefreshToken;
import com.example.demo.Repository.token.TokenRepository;
import com.example.demo.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TokenService {
    private final TokenRepository tokenRepository;
    private final JwtTokenUtil jwtTokenUtil;
    public void save(String token){
        RefreshToken refreshToken = new RefreshToken(token);
        tokenRepository.save(refreshToken);
    }

    public void deleteToken(RefreshTokenDto refreshToken) {
        RefreshToken deleteToken = tokenRepository.findByToken(refreshToken.getRefreshToken());
        //access token은 브라우저에서 삭제해야함
        tokenRepository.delete(deleteToken);
    }

    public String GetTokenId(String token){
        return jwtTokenUtil.getclaims(token).getSubject();
    }
}
