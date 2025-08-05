package com.minhphung.taskmanagementsystem.auth.controller;

import com.minhphung.taskmanagementsystem.auth.payload.request.LogInRequest;
import com.minhphung.taskmanagementsystem.auth.service.AuthService;
import com.minhphung.taskmanagementsystem.auth.payload.request.SignUpRequest;
import com.minhphung.taskmanagementsystem.auth.payload.response.JwtResponse;
import com.minhphung.taskmanagementsystem.auth.payload.response.MessageResponse;
import com.minhphung.taskmanagementsystem.core.dto.UserDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest){
        try {
            UserDto createdUser = authService.registerUser(signUpRequest);
            return ResponseEntity.ok(createdUser);
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: " + ex.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LogInRequest logInRequest){
        try{
            JwtResponse jwtResponse = authService.authenticateUser(logInRequest);
            return ResponseEntity.ok(jwtResponse);
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: " + ex.getMessage()));
        }
    }
}