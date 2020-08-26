package com.zzxka.jhz.security.util;

import com.zzxka.jhz.system.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author: zzxka
 * @date: 2020-08-11
 * @description:
 */
@Component
public class JwtTokenUtil {
    @Value("${sys.jwt.secret}")
    private String secret;
    @Value("${sys.jwt.expire}")
    private long expire;
    @Value("${sys.jwt.prefix}")
    private String prefix;
    
    
    public String createAccessToken(User user){
        // 生成token
        String token= Jwts.builder()
                .setId(user.getId()+"")
                .setSubject(user.getLoginName())
                .setIssuedAt(new Date())
                .setIssuer(prefix)
                .setExpiration(new Date(System.currentTimeMillis()+expire))
                .signWith(SignatureAlgorithm.HS256,secret.getBytes())
                .compact();
        return token;
    }

    public Claims parserToken(String token){
        return (Claims)Jwts.parser()
                .setSigningKey(secret.getBytes())
                .parse(token).getBody();
    }

}
