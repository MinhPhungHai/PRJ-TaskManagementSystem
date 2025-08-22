package com.minhphung.taskmanagementsystem.auth.security.services;

import com.minhphung.taskmanagementsystem.core.entity.Permission;
import com.minhphung.taskmanagementsystem.core.entity.User;
import com.minhphung.taskmanagementsystem.core.repository.PermissionRepository;
import com.minhphung.taskmanagementsystem.core.repository.UserRepository;
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
    UserRepository userRepository;

    @Autowired
    PermissionRepository permissionRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        List<Permission> permissions = permissionRepository.findAllByUsername(username);
        return UserDetailsImpl.build(user, permissions);
    }
}
