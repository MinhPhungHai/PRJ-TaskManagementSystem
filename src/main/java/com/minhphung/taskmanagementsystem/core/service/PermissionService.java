package com.minhphung.taskmanagementsystem.core.service;

import com.minhphung.taskmanagementsystem.core.dto.PermissionDto;

import java.util.List;

public interface PermissionService {
    List<PermissionDto> findAllPermissions(String username);
}
