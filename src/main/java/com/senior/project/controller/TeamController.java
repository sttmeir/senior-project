package com.senior.project.controller;


import com.senior.project.domain.User;
import com.senior.project.dto.UserCreateDto;
import com.senior.project.dto.UserDto;
import com.senior.project.enums.Role;
import com.senior.project.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
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
@RequestMapping("/api/team")
@RequiredArgsConstructor
@Tag(name = "Teams", description = "Operations related to team management")
public class TeamController {

    private final UserService userService;

    @Operation(summary = "Get the team of the manager", description = "Fetches all employees that belong to the supervisor's team. Only accessible by managers.")
    public ResponseEntity<List<UserDto>> getTeam(@AuthenticationPrincipal User manager) {
        Role role = manager.getRole();
        if(role != Role.MANAGER) {
            throw new AccessDeniedException("You do not have permission to access this resource. Only supervisors can!");
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.getTeamByManager(manager));
    }

    @PostMapping("/add")
    @Operation(summary = "Add a new employee", description = "Adds a new employee to the manager's team.")
    public ResponseEntity<UserDto> addEmployee(@AuthenticationPrincipal User manager, @RequestBody UserCreateDto userCreateDto) {
        Role role = manager.getRole();
        if(role != Role.MANAGER) {
            throw new AccessDeniedException("You do not have permission to access this resource. Only supervisors can!");
        }
        UserDto newUser = userService.createUser(userCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an employee by ID", description = "Deletes an employee from the manager's team by their unique ID.")
    public ResponseEntity<Void> deleteEmployee(@AuthenticationPrincipal User manager, @PathVariable Long id) {
        Role role = manager.getRole();
        if(role != Role.MANAGER) {
            throw new AccessDeniedException("You do not have permission to access this resource. Only supervisors can!");
        }
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}
