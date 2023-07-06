package com.example.security.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.Instant;
import java.util.*;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final String secretKey = "toan";
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
       String header = request.getHeader(AUTHORIZATION);
       if(header != null && header.startsWith("Bearer ")){
           try {
               String token = header.substring("Bearer ".length());
               Algorithm algorithm = Algorithm.HMAC256(secretKey.getBytes());
               JWTVerifier jwtVerifier = JWT.require(algorithm).build();
               DecodedJWT decodedJWT = jwtVerifier.verify(token);
               String email = decodedJWT.getSubject();
               String[] roles = decodedJWT.getClaim("role").asArray(String.class);
               Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
               Arrays.stream(roles).forEach(role -> authorities.add(new SimpleGrantedAuthority(role)));

               UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(email,null,authorities);
               SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

               filterChain.doFilter(request,response);
           }catch (Exception exception){
               response.setHeader("error",exception.getMessage());
               response.setStatus(FORBIDDEN.value());
               response.setContentType(APPLICATION_JSON_VALUE);
               Map<String,String> error = new HashMap<>();
               error.put("error message",exception.getMessage());
               new ObjectMapper().writeValue(response.getOutputStream(),error);
           }
       }else{
           filterChain.doFilter(request,response);
       }
    }
}
