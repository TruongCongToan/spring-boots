package com.example.security.controller;

import com.example.security.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
@RequiredArgsConstructor
public class DemoController {
    @GetMapping("/test")
    public ResponseEntity<String> login(){
        return ResponseEntity.ok("authentication and authorization is success");
    }

    @GetMapping("/jenkins")
    public ResponseEntity<String> test(){
        return ResponseEntity.ok("Test success fully !!!!!");
    }
}
