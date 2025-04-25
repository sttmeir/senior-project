package com.senior.project.dto.securitydto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class JwtRequestDto {
    public String username;
    public String password;
}
