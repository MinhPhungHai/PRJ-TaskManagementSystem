package com.minhphung.taskmanagementsystem.auth.security.services;

import com.minhphung.taskmanagementsystem.auth.config.RedisService;
import com.minhphung.taskmanagementsystem.core.dto.PermissionDto;
import com.minhphung.taskmanagementsystem.core.dto.UserDto;
import com.minhphung.taskmanagementsystem.core.service.PermissionService;
import com.minhphung.taskmanagementsystem.core.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    PermissionService permissionService;

    @Autowired
    RedisService redisService;

    @Autowired
    private UserService userService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDto user = userService.getUserByUsername(username);

        List<String> permissions = redisService.findPermissions(username);
        System.out.println("Get from Redis:\nUser: " + username + "\nPermissions: " + permissions);

        if (permissions == null){
            permissions = permissionService.findAllPermissions(username).stream()
                .map(PermissionDto::getName).toList();

            redisService.save(username, permissions);
            System.out.println("Get from DB\nUser: " + username + "\nPermissions: " + permissions);
        }

        return UserDetailsImpl.build(user, permissions);
    }
}
