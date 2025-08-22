package com.minhphung.taskmanagementsystem.core.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "m_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    //Log in credentials
    @Column(name="username",unique = true, nullable = false)
    String username;
    @Column(name="password", nullable = false)
    String password;

    //User information
    @Column(name="first_name")
    String firstName;
    @Column(name="last_name")
    String lastName;
    @Column(name="email",unique = true)
    @Email
    String email;

    @Column(name="manager_id")
    Long managerId;
    @Column(name="dept_id")
    int deptId;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_group_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "group_role_id")
    )
    Set<GroupRole> groupRoles;

    public User(String username, String email, String password, String firstName, String lastName) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
