SELECT DISTINCT
    u.username as "Username",
    gr.name AS "Group role name",
    --r.name AS "Role",
    p.name AS "Permission",
    p.id as "Permission ID"
FROM m_user u
         LEFT JOIN user_group_role ugr ON u.id = ugr.user_id
         LEFT JOIN group_role gr ON ugr.group_role_id = gr.id
         LEFT JOIN group_role_relation grr ON gr.id = grr.group_role_id
         LEFT JOIN m_role r ON grr.role_id = r.id
         LEFT JOIN role_permission rp ON r.id = rp.role_id
         LEFT JOIN permission p ON rp.permission_id = p.id
WHERE u.username IN ('alice')
order by p.id;
