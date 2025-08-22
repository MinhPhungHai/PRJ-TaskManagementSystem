package com.minhphung.taskmanagementsystem.core.repository;

import com.minhphung.taskmanagementsystem.core.entity.GroupRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRoleRepository extends JpaRepository<GroupRole, Long> {
    GroupRole findByName(String name);
}
