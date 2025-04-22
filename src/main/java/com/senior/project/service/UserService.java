package com.senior.project.service;

import com.senior.project.domain.User;
import com.senior.project.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<UserDto> getAllUsers();
    UserDto getUserById(Long id);
    void deleteUser(Long id);
    User saveUser(User user);
}
