package com.minhphung.taskmanagementsystem.core.mapper;

import com.minhphung.taskmanagementsystem.core.dto.UserDto;
import com.minhphung.taskmanagementsystem.core.entity.Role;
import com.minhphung.taskmanagementsystem.core.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);
    User toEntity(UserDto userDto);

}
