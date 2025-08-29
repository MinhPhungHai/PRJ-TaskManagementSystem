package com.minhphung.taskmanagementsystem.core.service.impl;

import com.minhphung.taskmanagementsystem.core.dto.PermissionDto;
import com.minhphung.taskmanagementsystem.core.entity.Permission;
import com.minhphung.taskmanagementsystem.core.mapper.PermissionMapper;
import com.minhphung.taskmanagementsystem.core.repository.PermissionRepository;
import com.minhphung.taskmanagementsystem.core.service.PermissionService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PermissionServiceImpl implements PermissionService {
    private PermissionRepository permissionRepository;
    private PermissionMapper permissionMapper;

    @Override
    public List<PermissionDto> findAllPermissions(String username) {
        List<Permission> permissions = permissionRepository.findAllByUsername(username);

        return permissions.stream().map(permissionMapper::toDto).collect(Collectors.toList());
    }
}
