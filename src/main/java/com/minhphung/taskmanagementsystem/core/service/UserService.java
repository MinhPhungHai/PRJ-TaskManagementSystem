package com.minhphung.taskmanagementsystem.core.service;

import com.minhphung.taskmanagementsystem.core.dto.UserDto;

import java.util.List;

public interface UserService {
    //Authentication
    boolean validateLogInCredentials(String username, String password);
    UserDto createUser(UserDto userDto);

    //Get Employee info
    List<UserDto> getAllUsers();
    UserDto getUserById(Long employeeId);
    UserDto getUserByUsername(String username);
}