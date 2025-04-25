package com.senior.project.controller;


import com.senior.project.domain.User;
import com.senior.project.dto.UserCreateDto;
import com.senior.project.dto.UserDto;
import com.senior.project.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@Tag(name = "Teams")
public class TeamController {

    private final UserService userService;

    // Получить всех сотрудников (доступ для MANAGER и ADMIN)
//    @PreAuthorize("hasRole('MANAGER') or hasRole('ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> getTeam(@AuthenticationPrincipal User manager) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.getTeamByManager(manager));
    }

//    // Добавить нового сотрудника (доступ только для ADMIN)
//    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<UserDto> addEmployee(@RequestBody UserCreateDto userCreateDto) {
        UserDto newUser = userService.createUser(userCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    // Удалить сотрудника (доступ только для ADMIN)
//    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}
