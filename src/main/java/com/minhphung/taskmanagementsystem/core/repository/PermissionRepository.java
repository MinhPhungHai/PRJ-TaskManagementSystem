package com.minhphung.taskmanagementsystem.core.repository;

import com.minhphung.taskmanagementsystem.core.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
    @Query(
            value = """
            SELECT DISTINCT p.*
            FROM m_user u
                     LEFT JOIN user_group_role ugr ON u.id = ugr.user_id
                     LEFT JOIN group_role gr ON ugr.group_role_id = gr.id
                     LEFT JOIN group_role_relation grr ON gr.id = grr.group_role_id
                     LEFT JOIN m_role r ON grr.role_id = r.id
                     LEFT JOIN role_permission rp ON r.id = rp.role_id
                     LEFT JOIN permission p ON rp.permission_id = p.id
            WHERE u.username = :username
            """,
            nativeQuery = true
    )
    List<Permission> findAllByUsername(@Param("username") String username);
}