package com.senior.project.service.impl;

import com.senior.project.config.JwtHelper;
import com.senior.project.domain.User;
import com.senior.project.dto.securitydto.JwtRequestDto;
import com.senior.project.dto.securitydto.RegisterUserDto;
import com.senior.project.enums.Role;
import com.senior.project.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserServiceImpl userService;
    private final AuthenticationManager authenticationManager;
    private final JwtHelper jwtHelper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public String login(JwtRequestDto authRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.getUsername(), authRequest.getPassword()
                )
        );
        UserDetails userDetails = userService.loadUserByUsername(authRequest.getUsername());
        return jwtHelper.generateToken(userDetails);
    }

    @Override
    public User signup(RegisterUserDto registrationUserDto) {
        User user = new User();
        user.setFullName(registrationUserDto.getName());
        user.setUsername(registrationUserDto.getUsername());
        user.setPassword(passwordEncoder.encode(registrationUserDto.getPassword()));
        user.setEmail(registrationUserDto.getEmail());
        user.setRole(Role.USER);
        return userService.saveUser(user);
    }
}
