package com.minhphung.taskmanagementsystem.core.service;

import com.minhphung.taskmanagementsystem.core.dto.UserDto;

import java.util.List;

public interface UserService {
    //Get User info
    List<UserDto> getAllUsers();
    UserDto getUserById(Long employeeId);
    UserDto getUserByUsername(String username);
    List<UserDto> getAllUsersByManagerId(Long managerId);

    //Update & Delete User info
    UserDto updateUser(Long employeeId, UserDto userDto);
    void deleteUser(Long employeeId);
}