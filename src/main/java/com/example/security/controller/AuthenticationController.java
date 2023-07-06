package com.example.security.controller;

import com.example.security.auth.AuthenticationRequest;
import com.example.security.auth.AuthenticationResponse;
import com.example.security.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        return ResponseEntity.ok(authenticationService.authenticate(authenticationRequest));
    }
}
