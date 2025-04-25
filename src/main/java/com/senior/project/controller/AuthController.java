package com.senior.project.controller;

import com.senior.project.domain.User;
import com.senior.project.dto.securitydto.JwtRequestDto;
import com.senior.project.dto.securitydto.JwtResponseDto;
import com.senior.project.dto.securitydto.RegisterUserDto;
import com.senior.project.service.impl.AuthServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Аутентификация")
@Slf4j
public class AuthController {

    private final AuthServiceImpl authService;

    @PostMapping("/login")
    @Operation(summary = "Вход в систему")
    public ResponseEntity<?> login(@Valid @RequestBody JwtRequestDto jwtRequestDto) {
        log.info("Login request received for user: {}", jwtRequestDto.getUsername());
        try {
            String token = authService.login(jwtRequestDto);
            log.info("Login successful for user: {}", jwtRequestDto.getUsername());
            return new ResponseEntity<>(new JwtResponseDto(token), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Login failed for user: {} - Error: {}", jwtRequestDto.getUsername(), e.getMessage());
            throw e;
        }
    }

    @PostMapping("/register")
    @Operation(summary = "Регистрация нового пользователя")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterUserDto registerUserDto) {
        User user = authService.signup(registerUserDto);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
}
