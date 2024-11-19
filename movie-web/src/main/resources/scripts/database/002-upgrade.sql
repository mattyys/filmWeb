#insertar datos de usuarios, artistas y films

INSERT INTO roles (name)
VALUES ('ADMIN'),
       ('USER');

INSERT INTO users (created_at, username, email, name, surname, password, birth_date, last_login_at)
VALUES ('2024-11-11 15:40:00', 'admin', 'admin@email.com', 'administrador', 'sruname-admin', 12345, '1990-11-11',
        '2024-11-11 15:40:00'),
       ('2024-11-11 15:41:00', 'user', 'user@email.com', 'usuario', 'sruname-user', 12345, '1990-11-11',
        '2024-11-11 15:41:00');

insert into users_with_roles (user_id, role_id)
    value (1, 1),
    (1, 2),
    (2, 2);

