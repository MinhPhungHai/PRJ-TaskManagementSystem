package com.minhphung.taskmanagementsystem.core.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto {
    Long userId;

    //Log in credentials
    String username;
    String password;

    //Employee information
    String firstName;
    String lastName;
    String email;

    //Employee position info
    Set<String> roles;
    Long managerId;
    int deptId;
}
