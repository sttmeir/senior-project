package com.senior.project.dto;

import com.senior.project.enums.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCreateDto {

    @NotBlank(message = "Email is required")
    private String email; // Email пользователя

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password; // Пароль пользователя

    @NotBlank(message = "Full name is required")
    private String fullName; // Полное имя пользователя

    @NotNull(message = "Role is required")
    private Role role; // Роль пользователя (USER, MANAGER, ADMIN)

    private Long supervisorId;
}
