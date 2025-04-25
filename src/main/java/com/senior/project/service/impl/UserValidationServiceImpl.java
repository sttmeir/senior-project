package com.senior.project.service.impl;

import com.senior.project.dto.securitydto.RegisterUserDto;
import com.senior.project.exception.DuplicateEmailException;
import com.senior.project.exception.DuplicateUsernameException;
import com.senior.project.exception.PasswordsDoNotMatchException;
import com.senior.project.service.UserService;
import com.senior.project.service.UserValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserValidationServiceImpl implements UserValidationService {

    private final UserService userService;

    @Override
    public void validateUser(RegisterUserDto registerUserDto) {
        if(!registerUserDto.getPassword().equals(registerUserDto.getConfirmPassword())) {
            throw new PasswordsDoNotMatchException("Passwords do not match!");
        }
        if(userService.findByUsername(registerUserDto.getUsername()).isPresent()) {
            throw new DuplicateUsernameException("Email is already in use!");
        }
    }
}
