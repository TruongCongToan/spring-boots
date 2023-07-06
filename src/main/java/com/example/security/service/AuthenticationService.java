package com.example.security.service;

import com.example.security.auth.AuthenticationRequest;
import com.example.security.auth.AuthenticationResponse;
import com.example.security.entity.Role;
import com.example.security.entity.User;
import com.example.security.repository.RoleCustomRepo;
import com.example.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleCustomRepo roleCustomRepo;
    private final RefreshTokenService refreshTokenService;
    private final JwtService jwtService;
    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) throws Exception {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),authenticationRequest.getPassword()));
        User user = userRepository.findByEmail(authenticationRequest.getEmail()).orElseThrow(() -> new UsernameNotFoundException("User not found !!"));
        List<String> roleList = roleCustomRepo.getRoles(user);

        Set<Role> roleSet = new HashSet<>();
        roleList.forEach(role -> roleSet.add(new Role(role)));
        user.setRoles(roleSet);

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        roleSet.forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));

        String jwtToken = jwtService.generateToken(user,authorities);
        String jwtRefreshToken = jwtService.generateRefreshToken(user);
        var refreshToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0cnVvbmd0b2FuIiwiZXhwIjoxNjg4NTQxNjU1fQ.WLlcKZyqrwCa_axgST3I2DKcLoLhKSiiTeZlezdbq1M";
        refreshTokenService.saveTokenInfo(refreshToken);

        return AuthenticationResponse.builder().token(jwtToken).refreshToken(jwtRefreshToken).build();
    }
}
