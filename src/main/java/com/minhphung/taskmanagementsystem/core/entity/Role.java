package com.minhphung.taskmanagementsystem.core.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer RoleId;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false, unique = true)
    ERole name;
}
