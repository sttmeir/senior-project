package com.senior.project.service;

import com.senior.project.dto.securitydto.RegisterUserDto;
import org.springframework.stereotype.Service;

@Service
public interface UserValidationService {
    public void validateUser(RegisterUserDto registerUserDto);
}
