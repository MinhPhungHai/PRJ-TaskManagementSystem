package com.minhphung.taskmanagementsystem.auth.service.impl;

import com.minhphung.taskmanagementsystem.auth.payload.request.LogInRequest;
import com.minhphung.taskmanagementsystem.auth.payload.request.SignUpRequest;
import com.minhphung.taskmanagementsystem.auth.payload.response.JwtResponse;
import com.minhphung.taskmanagementsystem.auth.security.jwt.JwtUtils;
import com.minhphung.taskmanagementsystem.auth.security.services.UserDetailsImpl;
import com.minhphung.taskmanagementsystem.auth.service.AuthService;
import com.minhphung.taskmanagementsystem.core.dto.UserDto;
import com.minhphung.taskmanagementsystem.core.entity.GroupRole;
import com.minhphung.taskmanagementsystem.core.entity.Role;
import com.minhphung.taskmanagementsystem.core.entity.User;
import com.minhphung.taskmanagementsystem.core.mapper.UserMapper;
import com.minhphung.taskmanagementsystem.core.repository.GroupRoleRepository;
import com.minhphung.taskmanagementsystem.core.repository.RoleRepository;
import com.minhphung.taskmanagementsystem.core.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    GroupRoleRepository groupRoleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserMapper userMapper;

    @Autowired
    JwtUtils jwtUtils;

    @Override
    public UserDto registerUser(SignUpRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            throw new RuntimeException("Username already taken");
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new RuntimeException("Email already in use");
        }

        User user = new User(
                signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                passwordEncoder.encode(signUpRequest.getPassword()),
                signUpRequest.getFirstName(),
                signUpRequest.getLastName()
        );

        Set<GroupRole> roles = new HashSet<>();
        GroupRole defaultRole = groupRoleRepository.findByName("USER");
        roles.add(defaultRole);

        user.setGroupRoles(roles);

        User savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);
    }


    @Override
    public JwtResponse authenticateUser(LogInRequest logInRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        logInRequest.getUsername(),
                        logInRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> permissions = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        return new JwtResponse(jwt);
    }
}
