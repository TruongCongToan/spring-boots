package com.example.security.service;

import com.example.security.auth.AuthenticationResponse;
import com.example.security.entity.RefreshToken;
import com.example.security.entity.User;

import java.util.Optional;

public interface RefreshTokenService {
//    public Optional<RefreshToken> findByToken(String token);
//    public AuthenticationResponse createToken(String refreshToken);
    public void saveTokenInfo(String refreshToken) throws Exception;
//    public int deleteByEmail(String userEmail);
}
