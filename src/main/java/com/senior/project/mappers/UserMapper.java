package com.senior.project.mappers;

import com.senior.project.domain.User;
import com.senior.project.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(source = "id", target = "userId")
    UserDto toUserDto(User user);

    @Mapping(source = "userId", target = "id")
    User toUserEntity(UserDto userDto);

    List<UserDto> toDtoList(List<User> users);

    List<User> toEntityList(List<UserDto> userDtos);
}
