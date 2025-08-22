package com.minhphung.taskmanagementsystem.core.mapper;

import com.minhphung.taskmanagementsystem.core.dto.PermissionDto;
import com.minhphung.taskmanagementsystem.core.entity.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    PermissionDto toDto(Permission permission);
    Permission toEntity(PermissionDto permissionDto);
}
