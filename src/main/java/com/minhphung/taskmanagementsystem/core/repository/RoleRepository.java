package com.minhphung.taskmanagementsystem.core.repository;

import com.minhphung.taskmanagementsystem.core.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    List<Role> findAllById(long id);
}