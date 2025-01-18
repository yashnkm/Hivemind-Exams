package com.v1.online.exam.controller;

import com.v1.online.exam.dto.LoginRequestDto;
import com.v1.online.exam.dto.RegisterRequestDto;
import com.v1.online.exam.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequestDto loginRequest) {
        return authService.login(loginRequest);
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequestDto registerRequest) {
        return authService.register(registerRequest);
    }
}
