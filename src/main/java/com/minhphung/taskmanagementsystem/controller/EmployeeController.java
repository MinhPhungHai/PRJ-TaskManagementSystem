package com.minhphung.taskmanagementsystem.controller;

import com.minhphung.taskmanagementsystem.dto.EmployeeDto;
import com.minhphung.taskmanagementsystem.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployees(){
        List<EmployeeDto> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/search/id/{employee_id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("employee_id") Long employeeId){
        EmployeeDto employeeDto= employeeService.getEmployeeById(employeeId);
        return ResponseEntity.ok(employeeDto);
    }

    @GetMapping("/search/username/{username}")
    public ResponseEntity<EmployeeDto> getEmployeeByUsername(@PathVariable("username") String username){
        EmployeeDto employeeDto= employeeService.getEmployeeByUsername(username);
        return ResponseEntity.ok(employeeDto);
    }


}