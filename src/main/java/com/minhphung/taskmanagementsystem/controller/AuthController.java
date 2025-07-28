package com.minhphung.taskmanagementsystem.controller;

import com.minhphung.taskmanagementsystem.dto.EmployeeDto;
import com.minhphung.taskmanagementsystem.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final EmployeeService employeeService;

    @PostMapping("/signup")
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto){
        EmployeeDto createdEmployeeDto = employeeService.createEmployee(employeeDto);
        return ResponseEntity.ok(createdEmployeeDto);
    }

    @PostMapping("/login")
    public ResponseEntity<Boolean> validateLogInCredentials(@RequestBody Map<String, String> loginCredentials){
        String username = loginCredentials.get("username");
        String password = loginCredentials.get("password");
        Boolean check = employeeService.validateLogInCredentials(username, password);
        return ResponseEntity.ok(check);
    }
}