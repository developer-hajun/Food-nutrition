package com.example.demo.Configuration;

import com.example.demo.Service.MemberService;
import com.example.demo.util.JwtTokenUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;


@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final String secretKey;
    private final MemberService memberService;
    private final JwtTokenUtil jwtTokenUtil;
    private final RedisConfig redisConfig;
    private Long expireTimeMs = 1000*60*60L;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String AccessToken = jwtTokenUtil.resolveAccessToken(request);
        LocalDateTime now = LocalDateTime.now();
        logger.info("authentication : {"+AccessToken+"}");
        if(AccessToken == null){
            logger.error("access token이 존재하지 않습니다");
            filterChain.doFilter(request,response);
            return;
        }
        if(jwtTokenUtil.validateToken(AccessToken)){
            UsernamePasswordAuthenticationToken authenticationToken = jwtTokenUtil.getAuthentication(AccessToken);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            LocalDateTime last = LocalDateTime.now();
            Duration diff = Duration.between(now, last);
            logger.info(diff.toString());
        }
        else {
            Long no1 = ((Number) jwtTokenUtil.getclaims(AccessToken).get("no")).longValue();
            System.out.println(no1);
            String refreshToken = redisConfig.redisTemplate().opsForValue().get(no1.toString());
            if(refreshToken == null){
                logger.error("토큰도만료 리스레스 토큰도 없거나 만료");
            }
            else{
                Claims getclaims = jwtTokenUtil.getclaims(refreshToken);
                Long no = ((Number) getclaims.get("no")).longValue();
                String id = getclaims.getSubject();
                String new_token = jwtTokenUtil.createToken(id, no, expireTimeMs);
                jwtTokenUtil.setHeaderAccessToken(response, new_token);
                System.out.println(new_token);
                System.out.println(jwtTokenUtil.getclaims(new_token).getSubject());
                UsernamePasswordAuthenticationToken authenticationToken = jwtTokenUtil.getAuthentication(refreshToken);
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }

        filterChain.doFilter(request,response);
    }


}
