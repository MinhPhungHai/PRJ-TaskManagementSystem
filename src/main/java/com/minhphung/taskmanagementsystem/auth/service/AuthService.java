package com.minhphung.taskmanagementsystem.auth.service;

import com.minhphung.taskmanagementsystem.auth.payload.request.SignUpRequest;
import com.minhphung.taskmanagementsystem.auth.payload.request.LogInRequest;
import com.minhphung.taskmanagementsystem.auth.payload.response.JwtResponse;
import com.minhphung.taskmanagementsystem.core.dto.UserDto;

public interface AuthService {
    UserDto registerUser(SignUpRequest signUpRequest);
    JwtResponse authenticateUser(LogInRequest logInRequest);
}
