package com.example.demo.Service;

import com.example.demo.Dto.RefreshTokenDto;
import com.example.demo.Entity.RefreshToken;
import com.example.demo.Repository.token.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RefreshTokenService {
    private final TokenRepository tokenRepository;
    public void save(String token){
        RefreshToken refreshToken = new RefreshToken(token);
        tokenRepository.save(refreshToken);
    }

    public void deleteToken(RefreshTokenDto refreshToken) {
        RefreshToken deleteToken = tokenRepository.findByToken(refreshToken.getRefreshToken());
        //access token은 브라우저에서 삭제해야함
        tokenRepository.delete(deleteToken);
    }
}
