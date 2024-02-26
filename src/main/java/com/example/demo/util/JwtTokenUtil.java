package com.example.demo.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtTokenUtil {
    public static Boolean isExpired(String token,String secretKey){
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody()
                .getExpiration().before(new Date());
    }
    public static String createToken(Long no,String key,long expireTimeMs){
        Claims claims = Jwts.claims();
        claims.put("no",no);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+expireTimeMs))
                .signWith(SignatureAlgorithm.HS256,key)
                .compact();
    }
    public static Long getNo(String token, String secretKey){
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token)
                .getBody().get("no", Long.class);
    }
}
