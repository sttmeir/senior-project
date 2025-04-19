package com.senior.project.service;

import com.senior.project.domain.User;
import com.senior.project.dto.securitydto.JwtRequestDto;
import com.senior.project.dto.securitydto.RegisterUserDto;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    public String login(JwtRequestDto authRequest);
    public User signup(RegisterUserDto registrationUserDto);
}
