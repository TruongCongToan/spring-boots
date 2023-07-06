package com.example.security.service.Impt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.security.auth.AuthenticationResponse;
import com.example.security.entity.RefreshToken;
import com.example.security.entity.Role;
import com.example.security.entity.User;
import com.example.security.repository.RefreshTokenRepository;
import com.example.security.repository.RoleCustomRepo;
import com.example.security.repository.UserRepository;
import com.example.security.service.JwtService;
import com.example.security.service.RefreshTokenService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;

@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final String secretKey = "toan";
    private final UserRepository userRepository;
    private final RoleCustomRepo roleCustomRepo;
    @Override
    public void saveTokenInfo(String refreshToken) throws Exception {
        RefreshToken refreshTokenGet = refreshTokenRepository.findByRefreshToken(refreshToken).orElse(null);
        assert refreshTokenGet != null;
//        RefreshToken refreshToken1 = getRefreshTokenInFo(refreshTokenGet.getRefreshToken());
        Instant exprireTime = refreshTokenGet.getExpiryDate();
        System.out.println(refreshTokenGet);
        var check = exprireTime.compareTo(Instant.now());
        System.out.println(check);
    }
}
