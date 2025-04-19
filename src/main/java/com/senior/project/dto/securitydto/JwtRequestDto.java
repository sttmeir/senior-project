package com.senior.project.dto.securitydto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtRequestDto {
    public String username;
    public String password;
}
