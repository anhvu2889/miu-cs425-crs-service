INSERT INTO `roles`(role_id, role_name, description)
VALUES (1, 'SUPER_ADMIN', 'Super Admin'),
       (2, 'ADMIN', 'Admin'),
       (3, 'USER', 'User');

INSERT INTO `users`(user_id, username, password, firstname, lastname, email, address, phone, created_at, updated_at)
VALUES (1, 'admin', '$2a$10$4zu5oGYmEHgUTK8b5vcOgegsd8.99Ex0x3amg7.TOqSFyqgYQgcyy', 'Admin', 'Admin', 'admin@miu.edu',
        'Fairfield, Iowa', '[641]-244-1234', '2024-07-26 16:26:29', '2024-07-26 16:26:30'),
       (2, 'superadmin', '$2a$10$IQsAnGA1ZcKDVLSM2fo5u.kznCwhBrN1RPmfScjGxIznHki50jPN2', 'Super Admin', '',
        'superadmin@miu.edu', 'Fairfield, Iowa', '[641]-244-1235', '2024-07-26 16:31:11', '2024-07-26 16:31:11');

INSERT INTO user_roles(user_id, role_id)
values (1, 1),
       (2, 2);