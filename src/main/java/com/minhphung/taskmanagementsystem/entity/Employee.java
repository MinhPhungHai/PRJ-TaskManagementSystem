package com.minhphung.taskmanagementsystem.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long employeeId;

    //Log in credentials
    @Column(name="username",unique = true, nullable = false)
    String username;
    @Column(name="password", nullable = false)
    String password;

    //Employee information
    @Column(name="first_name", nullable = false)
    String firstName;
    @Column(name="last_name")
    String lastName;
    @Column(name="email",unique = true)
    String email;

    //Employee position info
    @Column(name="role", nullable = false)
    String role;

    @Column(name="manager_id")
    Long managerId;
    @Column(name="dept_id", nullable = false)
    int deptId;
}
