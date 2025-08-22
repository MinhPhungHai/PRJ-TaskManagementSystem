package com.minhphung.taskmanagementsystem.core.service.impl;

import com.minhphung.taskmanagementsystem.core.dto.UserDto;
import com.minhphung.taskmanagementsystem.core.entity.User;
import com.minhphung.taskmanagementsystem.core.exception.ResourceNotFoundException;
import com.minhphung.taskmanagementsystem.core.mapper.UserMapper;
import com.minhphung.taskmanagementsystem.core.repository.UserRepository;
import com.minhphung.taskmanagementsystem.core.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private UserMapper userMapper;

    @Override
    public List<UserDto> getAllUsers() {
        return List.of();
    }

    /**
     * Retrieves an employee by their ID.
     *
     * @param employeeId Long
     * @return the corresponding {@link UserDto}
     * @throws ResourceNotFoundException if no employee is found with the given ID
     */
    @Override
    public UserDto getUserById(Long employeeId) {
        User user = userRepository.findById(employeeId)
                .orElseThrow(()-> new ResourceNotFoundException("There is no user with id: " + employeeId));
        return userMapper.toDto(user);
    }

    /**
     * Retrieves an employee by their username.
     *
     * @param username String
     * @return the corresponding {@link UserDto}
     * @throws ResourceNotFoundException if no employee is found with the given username
     */

    @Override
    public UserDto getUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(()-> new ResourceNotFoundException("There is no user with username: " + username));
        return userMapper.toDto(user);
    }

    @Override
    public List<UserDto> getAllUsersByManagerId(Long managerId) {
        List<User> users = userRepository.findAllByManagerId(managerId);
        return users.stream().map(userMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(Long employeeId, UserDto userDto) {
        User user = userRepository.findById(employeeId)
                .orElseThrow(()-> new ResourceNotFoundException("There is no user with id: " + employeeId));

        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setManagerId(userDto.getManagerId());
        user.setDeptId(userDto.getDeptId());

        User savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);
    }

    @Override
    public void deleteUser(Long employeeId) {
        User user = userRepository.findById(employeeId)
                .orElseThrow(()-> new ResourceNotFoundException("There is no user with id: " + employeeId));
        userRepository.delete(user);
    }
}