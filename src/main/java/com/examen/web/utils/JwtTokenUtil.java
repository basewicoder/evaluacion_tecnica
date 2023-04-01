package com.examen.web.utils;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;


@Slf4j
public class JwtTokenUtil {


    public static final long EXPIRATION = 24 * 3600 * 7 * 1000;

    public static final String SECRET_KEY = "soccer_secret_f98u1o0124";


    private static final String USER_NAME = "username";
    private static final String USER_ID = "userId";
    private static final String NICK_NAME = "nickName";


    public static String createToken(String username) {
        return Jwts
                .builder()
                .setSubject(username)
                .claim(USER_NAME, username)
//                .claim(USER_ID, userId)
//                .claim(NICK_NAME, nickName)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }



    public static boolean validate(String token) {
        try {
            Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
            return claims.getExpiration().after(new Date());
        } catch (Exception e) {
            log.error("validate token error:{} ", e.getMessage());
            return false;
        }
    }


    public static String getUsername(String token) {
        Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        return claims.get(USER_NAME).toString();
    }


    public static String getNickName(String token) {
        Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        return claims.get(NICK_NAME).toString();
    }


    public static String getUserId(String token) {
        Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        return claims.get(USER_ID).toString();
    }
}
