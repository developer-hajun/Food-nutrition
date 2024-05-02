package com.example.demo.Configuration;

import com.example.demo.Service.MemberService;
import com.example.demo.util.JwtTokenUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final String secretKey;
    private final MemberService memberService;
    private final JwtTokenUtil jwtTokenUtil;
    private Long expireTimeMs = 1000*60*60L;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String AccessToken = jwtTokenUtil.resolveAccessToken(request);
        final String refreshToken = jwtTokenUtil.resolveRefreshToken(request);
        logger.info("authentication : {"+AccessToken+"}");
        if(AccessToken == null){
            logger.error("access token이 존재하지 않습니다");
            filterChain.doFilter(request,response);
            return;
        }
        if(jwtTokenUtil.validateToken(AccessToken)){
            UsernamePasswordAuthenticationToken authenticationToken = jwtTokenUtil.getAuthentication(AccessToken);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        else if(refreshToken!=null && jwtTokenUtil.validateToken(refreshToken) && jwtTokenUtil.existRefreshKey(refreshToken)) {
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
        else{
            logger.error("토큰도만료 리스레스 토큰도 없거나 만료");
        }
        filterChain.doFilter(request,response);
    }


}
