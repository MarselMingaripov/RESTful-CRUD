INSERT INTO t_role values (1, 'ROLE_ADMIN', 'admin');
INSERT INTO t_role values (2, 'ROLE_MODERATOR', 'moderator');
INSERT INTO t_role values (3, 'ROLE_USER', 'user');
INSERT INTO t_user values (1, 'admin@mail.ru', '$2a$12$WT1jnbWtEmlFQP.wQVzPx.G7/4ceTSHc5TCeEbY2bhzpyKsDkSZb6', 'admin');
INSERT INTO user_roles values (1, 1)