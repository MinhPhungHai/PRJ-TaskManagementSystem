package com.minhphung.taskmanagementsystem.core.repository;

import com.minhphung.taskmanagementsystem.core.entity.ERole;
import com.minhphung.taskmanagementsystem.core.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(ERole name);
}