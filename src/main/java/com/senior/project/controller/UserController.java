package com.senior.project.controller;

import com.senior.project.domain.User;
import com.senior.project.dto.UserDto;
import com.senior.project.enums.Role;
import com.senior.project.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(name = "Users", description = "Operations related to users")
public class UserController {

    private final UserService userService;

    @GetMapping
    @Operation(summary = "Get all users", description = "Fetches all users from the system. Only accessible by users with 'ADMIN' role.")
    public ResponseEntity<List<UserDto>> getAllUsers(@AuthenticationPrincipal User user) {
        Role userRole = user.getRole();
        if (userRole != Role.ADMIN) {
            throw new AccessDeniedException("Access denied. You do not have permission to view all users.");
        }
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get user by ID", description = "Fetches a user by their unique ID.")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete user by ID", description = "Deletes a user from the system by their unique ID.")
    public ResponseEntity<Void> deleteUser(@AuthenticationPrincipal User user, @PathVariable Long id) {
        Role userRole = user.getRole();
        if (userRole != Role.ADMIN) {
            throw new AccessDeniedException("Access denied. You do not have permission to delete users.");
        }
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}