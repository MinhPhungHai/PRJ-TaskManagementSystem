package com.minhphung.taskmanagementsystem.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeDto {
    Long employeeId;

    //Log in credentials
    String username;
    String password;

    //Employee information
    String firstName;
    String lastName;
    String email;

    //Employee position info
    String role;
    Long managerId;
    int deptId;
}
