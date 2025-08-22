package com.minhphung.taskmanagementsystem.core.mapper;

import com.minhphung.taskmanagementsystem.core.dto.GroupRoleDto;
import com.minhphung.taskmanagementsystem.core.entity.GroupRole;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GroupRoleMapper {
    GroupRoleDto toDto(GroupRole groupRole);
    GroupRole toEntity(GroupRoleDto groupRoleDto);
}
