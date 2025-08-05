package com.minhphung.taskmanagementsystem.core.mapper;

import com.minhphung.taskmanagementsystem.core.dto.UserDto;
import com.minhphung.taskmanagementsystem.core.entity.ERole;
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

    @Mapping(target = "roles", expression = "java(mapRolesFromStrings(userDto.getRoles()))")
    User toEntity(UserDto userDto);

    default Set<String> mapRoles(Set<Role> roles) {
        return roles.stream()
                .map(role -> role.getName().name()) // enum to String
                .collect(Collectors.toSet());
    }

    default Set<Role> mapRolesFromStrings(Set<String> roleStrings) {
        if (roleStrings == null) return new HashSet<>();
        return roleStrings.stream()
                .map(roleStr -> {
                    Role role = new Role();
                    role.setName(ERole.valueOf(roleStr)); // string to ERole
                    return role;
                })
                .collect(Collectors.toSet());
    }
}
