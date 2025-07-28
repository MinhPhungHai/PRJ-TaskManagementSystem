package com.minhphung.taskmanagementsystem.service.impl;

import com.minhphung.taskmanagementsystem.dto.EmployeeDto;
import com.minhphung.taskmanagementsystem.entity.Employee;
import com.minhphung.taskmanagementsystem.exception.ResourceNotFoundException;
import com.minhphung.taskmanagementsystem.mapper.EmployeeMapper;
import com.minhphung.taskmanagementsystem.repository.EmployeeRepository;
import com.minhphung.taskmanagementsystem.service.EmployeeService;
import com.minhphung.taskmanagementsystem.util.Encryption;
import com.minhphung.taskmanagementsystem.util.Encryption;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;
    private EmployeeMapper employeeMapper;

    /**
     * Creates and saves a new employee in the system.
     *
     * @param employeeDto the employee data to create
     * @return the created {@link EmployeeDto} with generated ID and persisted data
     */
    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        //Encrypt the password
        String EncryptedPassword = Encryption.md5Encryption(employeeDto.getPassword());
        employeeDto.setPassword(EncryptedPassword);

        Employee employee = employeeMapper.toEntity(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        return employeeMapper.toDto(savedEmployee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map(employeeMapper::toDto).collect(Collectors.toList());
    }

    /**
     * Validate the input log in credentials (username and password)
     *
     * @param username String
     * @param password String
     * @return boolean
     * @throws ResourceNotFoundException if no employee is found with the given username
     */
    @Override
    public boolean validateLogInCredentials(String username, String password) {
        EmployeeDto employeeDto = getEmployeeByUsername(username);
        String EncryptedPassword = Encryption.md5Encryption(password);

        return employeeDto.getPassword().equals(EncryptedPassword);
    }

    /**
     * Retrieves an employee by their ID.
     *
     * @param employeeId Long
     * @return the corresponding {@link EmployeeDto}
     * @throws ResourceNotFoundException if no employee is found with the given ID
     */
    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(()-> new ResourceNotFoundException("There is no user with id: " + employeeId));
        return employeeMapper.toDto(employee);
    }

    /**
     * Retrieves an employee by their username.
     *
     * @param username String
     * @return the corresponding {@link EmployeeDto}
     * @throws ResourceNotFoundException if no employee is found with the given username
     */

    @Override
    public EmployeeDto getEmployeeByUsername(String username) {
        Employee employee = employeeRepository.findByUsername(username)
                .orElseThrow(()-> new ResourceNotFoundException("There is no user with username: " + username));
        return employeeMapper.toDto(employee);
    }
}