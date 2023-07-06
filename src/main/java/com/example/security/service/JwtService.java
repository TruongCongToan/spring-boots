package com.example.security.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.security.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;
@Service
public class JwtService {
    private final String secretKey = "toan";
    public String generateToken(User user, Collection<SimpleGrantedAuthority> authorities){
        Algorithm algorithm = Algorithm.HMAC256(secretKey.getBytes());
        return JWT.create()
                .withSubject(user.getEmail())
                .withClaim("role",authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .withExpiresAt(new Date(System.currentTimeMillis() + 5*60*1000))
                .sign(algorithm);
    }
    public String generateRefreshToken(User user){
        Algorithm algorithm = Algorithm.HMAC256(secretKey.getBytes());
        return JWT.create()
                .withSubject(user.getEmail())
                .withExpiresAt(new Date(System.currentTimeMillis() + 10*60*1000))
                .sign(algorithm);
    }
}
