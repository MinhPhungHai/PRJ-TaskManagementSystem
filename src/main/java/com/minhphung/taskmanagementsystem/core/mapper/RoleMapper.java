package com.minhphung.taskmanagementsystem.core.mapper;

import com.minhphung.taskmanagementsystem.core.dto.RoleDto;
import com.minhphung.taskmanagementsystem.core.entity.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleDto toDto(Role role);
    Role toEntity(RoleDto roleDto);

}