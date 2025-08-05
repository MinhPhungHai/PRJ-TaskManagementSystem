package com.minhphung.taskmanagementsystem.core.service.impl;

import com.minhphung.taskmanagementsystem.core.dto.UserDto;
import com.minhphung.taskmanagementsystem.core.entity.User;
import com.minhphung.taskmanagementsystem.core.exception.ResourceNotFoundException;
import com.minhphung.taskmanagementsystem.core.mapper.UserMapper;
import com.minhphung.taskmanagementsystem.core.repository.UserRepository;
import com.minhphung.taskmanagementsystem.core.service.UserService;
import com.minhphung.taskmanagementsystem.util.Encryption;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private UserMapper userMapper;

    /**
     * Creates and saves a new employee in the system.
     *
     * @param userDto the employee data to create
     * @return the created {@link UserDto} with generated ID and persisted data
     */
    @Override
    public UserDto createUser(UserDto userDto) {
        //Encrypt the password
        String EncryptedPassword = Encryption.md5Encryption(userDto.getPassword());
        userDto.setPassword(EncryptedPassword);

        User user = userMapper.toEntity(userDto);
        User savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(userMapper::toDto).collect(Collectors.toList());
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
        UserDto userDto = getUserByUsername(username);
        String EncryptedPassword = Encryption.md5Encryption(password);

        return userDto.getPassword().equals(EncryptedPassword);
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
}