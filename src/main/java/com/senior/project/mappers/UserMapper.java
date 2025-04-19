package com.senior.project.mappers;

import com.senior.project.domain.User;
import com.senior.project.dto.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toUserDto(User user);
    User toEntity(UserDto dto);
}
