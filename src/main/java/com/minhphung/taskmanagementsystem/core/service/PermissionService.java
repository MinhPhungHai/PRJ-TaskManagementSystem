package com.minhphung.taskmanagementsystem.core.service;

import com.minhphung.taskmanagementsystem.core.dto.PermissionDto;
import com.minhphung.taskmanagementsystem.core.entity.Permission;

import java.util.List;

public interface PermissionService {
    List<PermissionDto> getAllPermissionsByUsername(String username);
}
