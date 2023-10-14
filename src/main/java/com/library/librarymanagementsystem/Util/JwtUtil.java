package com.library.librarymanagementsystem.Util;

import com.library.librarymanagementsystem.Dto.UserDto;
import com.library.librarymanagementsystem.Entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.supper.secret}")
    private  String secKey;
    @Value("${jwt.expiration.time}")
    private String expirationTime;
    public String generateToken(UserDto userDto) {
        return Jwts.builder()
                .setSubject(userDto.getUsername())
                .claim("role", userDto.getRole())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + secKey))
                .signWith(SignatureAlgorithm.HS256, expirationTime)
                .compact();
    }

}
