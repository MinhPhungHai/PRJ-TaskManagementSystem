-- -- USERS
-- INSERT INTO m_user (id, username, password, first_name, last_name, email, manager_id, dept_id)
-- VALUES
--     (1, 'alice', 'pass123', 'Alice', 'Nguyen', 'alice@example.com', NULL, 1),
--     (2, 'bob', 'pass123', 'Bob', 'Tran', 'bob@example.com', 1, 1),
--     (3, 'charlie', 'pass123', 'Charlie', 'Le', 'charlie@example.com', 2, 2),

-- GROUP ROLES
INSERT INTO group_role (id, name)
VALUES
    (1, 'ADMIN'),
    (2, 'MANAGER'),
    (3, 'USER');

-- ROLES
INSERT INTO m_role (id, name)
VALUES
    (1,'MANAGE_TASK'),   -- permission from 1–6
    (2,'QUERY_USER'),    -- permission from 8–10
    (3,'MODIFY_USER');   -- permission from 7–12

-- PERMISSIONS
INSERT INTO permission (id, name)
VALUES
    (1, 'CREATE_TASK'),
    (2, 'GET_ALL_TASKS'),
    (3, 'GET_TASK'),
    (4, 'UPDATE_TASK'),
    (5, 'MARK_COMPLETED'),
    (6, 'DELETE_TASK'),
    (7, 'CREATE_USER'),
    (8, 'GET_USER'),
    (9, 'GET_ALL_SUBORDINATES'),
    (10,'GET_ALL_USERS'),
    (11,'UPDATE_USER'),
    (12,'DELETE_USER');


-- USER ↔ GROUP_ROLE (assign users to group roles)
INSERT INTO user_group_role (user_id, group_role_id)
VALUES
    (1, 1), -- Alice → ADMIN
    (2, 2), -- Bob → MANAGER
    (3, 3); -- Charlie → USER

-- GROUP_ROLE ↔ ROLE (link group_role to roles)
-- ADMIN: manage_task, query_user, modify_user
-- MANAGER: manage_task, query_user
-- USER: manage_task
INSERT INTO group_role_relation (group_role_id, role_id)
VALUES
    (1, 1),
    (1, 2),
    (1, 3),
    (2, 1),
    (2, 2),
    (3, 1);

-- ROLE ↔ PERMISSION (link roles to permissions)
-- manage_task: permissions 1–6
INSERT INTO role_permission (role_id, permission_id)
VALUES
    (1, 1),
    (1, 2),
    (1, 3),
    (1, 4),
    (1, 5),
    (1, 6);

-- query_user: permissions 8–10
INSERT INTO role_permission (role_id, permission_id)
VALUES
    (2, 8),
    (2, 9),
    (2, 10);

-- modify_user: permissions 7–12
INSERT INTO role_permission (role_id, permission_id)
VALUES
    (3, 7),
    (3, 8),
    (3, 9),
    (3, 10),
    (3, 11),
    (3, 12);
