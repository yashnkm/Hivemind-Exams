package com.v1.online.exam.service;

import com.v1.online.exam.config.JwtUtil;
import com.v1.online.exam.dto.LoginRequestDto;
import com.v1.online.exam.dto.RegisterRequestDto;
import com.v1.online.exam.entity.Users;
import com.v1.online.exam.repository.UsersRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UsersRepository usersRepository;

    public AuthService(AuthenticationManager authenticationManager, JwtUtil jwtUtil, UsersRepository usersRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.usersRepository = usersRepository;
    }

    public String login(LoginRequestDto loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getName(),
                        loginRequest.getPassword()
                )
        );

        // Fetch user role
        Users user = usersRepository.findByName(loginRequest.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Generate and return the JWT token with role
        return jwtUtil.generateToken(authentication.getName(), user.getRole().name());
    }

    public String register(RegisterRequestDto registerRequest) {
        if (usersRepository.findByName(registerRequest.getName()).isPresent()) {
            throw new RuntimeException("Name already exists");
        }

        Users user = new Users();
        user.setName(registerRequest.getName());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(registerRequest.getPassword()));
        user.setRole(Users.Role.valueOf(registerRequest.getRole().toUpperCase()));

        usersRepository.save(user);

        return "User registered successfully!";
    }
}
