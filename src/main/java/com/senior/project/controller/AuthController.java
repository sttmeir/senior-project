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
@Tag(name = "Authentication", description = "Operations related to user authentication and registration")
@Slf4j
public class AuthController {

    private final AuthServiceImpl authService;

    /**
     * Endpoint for logging in to the system.
     *
     * @param jwtRequestDto Contains the username and password for login.
     * @return JWT token if login is successful.
     * @throws Exception If login fails due to incorrect credentials.
     */
    @PostMapping("/login")
    @Operation(summary = "User login", description = "Authenticate the user with their username and password to receive a JWT token.")
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

    /**
     * Endpoint for registering a new user.
     *
     * @param registerUserDto Contains the details of the user to be registered.
     * @return The registered user object.
     */
    @PostMapping("/register")
    @Operation(summary = "User registration", description = "Register a new user with the provided details.")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterUserDto registerUserDto) {
        User user = authService.signup(registerUserDto);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
}
