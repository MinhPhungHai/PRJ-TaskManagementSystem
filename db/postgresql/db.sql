-- USER TABLE
CREATE TABLE m_user (
                        id BIGSERIAL PRIMARY KEY,
                        username VARCHAR(255) NOT NULL UNIQUE,
                        password VARCHAR(255) NOT NULL,
                        first_name VARCHAR(255),
                        last_name VARCHAR(255),
                        email VARCHAR(255) UNIQUE,
                        manager_id BIGINT,
                        dept_id INT,
                        CONSTRAINT fk_user_manager FOREIGN KEY (manager_id) REFERENCES m_user(id)
);

-- GROUP_ROLE TABLE
CREATE TABLE group_role (
                            id BIGSERIAL PRIMARY KEY,
                            name VARCHAR(255) NOT NULL UNIQUE
);

-- USER ↔ GROUP_ROLE (Join Table)
CREATE TABLE user_group_role (
                                 user_id BIGINT NOT NULL,
                                 group_role_id BIGINT NOT NULL,
                                 PRIMARY KEY (user_id, group_role_id),
                                 FOREIGN KEY (user_id) REFERENCES m_user(id) ON DELETE CASCADE,
                                 FOREIGN KEY (group_role_id) REFERENCES group_role(id) ON DELETE CASCADE
);

-- ROLE TABLE
CREATE TABLE m_role (
                        id BIGSERIAL PRIMARY KEY,
                        name VARCHAR(255) NOT NULL UNIQUE
);

-- GROUP_ROLE ↔ ROLE (Join Table)
CREATE TABLE group_role_relation (
                                     group_role_id BIGINT NOT NULL,
                                     role_id BIGINT NOT NULL,
                                     PRIMARY KEY (group_role_id, role_id),
                                     FOREIGN KEY (group_role_id) REFERENCES group_role(id) ON DELETE CASCADE,
                                     FOREIGN KEY (role_id) REFERENCES m_role(id) ON DELETE CASCADE
);

-- PERMISSION TABLE
CREATE TABLE permission (
                            id BIGSERIAL PRIMARY KEY,
                            name VARCHAR(255) NOT NULL UNIQUE
);

-- ROLE ↔ PERMISSION (Join Table)
CREATE TABLE role_permission (
                                 role_id BIGINT NOT NULL,
                                 permission_id BIGINT NOT NULL,
                                 PRIMARY KEY (role_id, permission_id),
                                 FOREIGN KEY (role_id) REFERENCES m_role(id) ON DELETE CASCADE,
                                 FOREIGN KEY (permission_id) REFERENCES permission(id) ON DELETE CASCADE
);


CREATE TABLE task (
                       id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                       title VARCHAR(255) NOT NULL,
                       description TEXT,
                       deadline TIMESTAMP NOT NULL,
                       urgent BOOLEAN,
                       start_time TIMESTAMP,
                       end_time TIMESTAMP,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       completed BOOLEAN DEFAULT FALSE,
                       done_at TIMESTAMP,
                       user_id BIGINT
);
