package com.senior.project.service.impl;

import com.senior.project.domain.User;
import com.senior.project.dto.securitydto.JwtRequestDto;
import com.senior.project.dto.securitydto.RegisterUserDto;
import com.senior.project.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Override
    public String login(JwtRequestDto authRequest) {
        return "";
    }

    @Override
    public User signup(RegisterUserDto registrationUserDto) {
        return null;
    }
}
