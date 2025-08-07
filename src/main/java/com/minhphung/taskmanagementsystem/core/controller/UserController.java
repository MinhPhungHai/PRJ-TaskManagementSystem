package com.minhphung.taskmanagementsystem.core.controller;

import com.minhphung.taskmanagementsystem.core.dto.UserDto;
import com.minhphung.taskmanagementsystem.core.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@AllArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/search/id/{user_id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<UserDto> getUserById(@PathVariable("user_id") Long employeeId){
        UserDto userDto = userService.getUserById(employeeId);
        return ResponseEntity.ok(userDto);
    }

    @GetMapping("/search/username/{username}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<UserDto> getUserByUsername(@PathVariable("username") String username){
        UserDto userDto = userService.getUserByUsername(username);
        return ResponseEntity.ok(userDto);
    }

    @GetMapping("/search/subordinates/{manager_id}")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public ResponseEntity<List<UserDto>> getAllUsersByManagerId(@PathVariable("manager_id") Long managerId){
        List<UserDto> users = userService.getAllUsersByManagerId(managerId);
        return ResponseEntity.ok(users);
    }

    @PutMapping("/update/{user_id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<UserDto> updateUser(@PathVariable("user_id") Long userId, @RequestBody UserDto userDto){
        UserDto updatedUserDto = userService.updateUser(userId, userDto);
        return ResponseEntity.ok(updatedUserDto);
    }

    @DeleteMapping("/delete/{user_id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<String> deleteUser(@PathVariable("user_id") Long userId){
        userService.deleteUser(userId);
        return ResponseEntity.ok("User with ID: " + userId + " has been deleted!");
    }
}