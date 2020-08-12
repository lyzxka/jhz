package com.zzxka.jhz.security.util;

import com.zzxka.jhz.system.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * @author: zzxka
 * @date: 2020-08-11
 * @description:
 */
public class JwtTokenUtil {
    public static String createAccessToken(User user){
        // 生成token
        String token= Jwts.builder()
                .setId(user.getId()+"")
                .setSubject(user.getLoginName())
                .setIssuedAt(new Date())
                .setIssuer("jhz")
                .setExpiration(new Date(System.currentTimeMillis()+1000))
                .signWith(SignatureAlgorithm.HS256,"123456".getBytes())
                .compact();
        return token;
    }
}
