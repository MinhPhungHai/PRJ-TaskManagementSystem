package com.minhphung.taskmanagementsystem.service;

import com.minhphung.taskmanagementsystem.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    //Authentication
    boolean validateLogInCredentials(String username, String password);
    EmployeeDto createEmployee(EmployeeDto employeeDto);

    //Get Employee info
    List<EmployeeDto> getAllEmployees();
    EmployeeDto getEmployeeById(Long employeeId);
    EmployeeDto getEmployeeByUsername(String username);
}