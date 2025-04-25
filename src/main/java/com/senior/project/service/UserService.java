package com.senior.project.service;

import com.senior.project.domain.User;
import com.senior.project.dto.UserCreateDto;
import com.senior.project.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
    List<UserDto> getAllUsers();
    UserDto getUserById(Long id);
    void deleteUser(Long id);
    User saveUser(User user);
    List<UserDto> getTeamByManager(User manager);
    UserDto createUser(UserCreateDto userCreateDto);
    Optional<User> findByUsername(String username);
}
