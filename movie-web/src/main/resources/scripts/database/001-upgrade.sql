create table if not exists films
(
    id           bigint primary key auto_increment not null,
    title        varchar(255)                      not null,
    release_year int                               not null,
    poster       varchar(255)                      not null,

);
create table if not exists users
(
    id            bigint primary key auto_increment not null,
    created_at    datetime                          not null,
    username      varchar(255)                      not null,
    email         varchar(255)                      not null,
    name          varchar(255)                      not null,
    password      varchar(255)                      not null,
    birth_date    date                              not null,
    last_login_at datetime                          not null

);

create table if not exists roles
(
    id   bigint unsigned not null auto_increment primary key,
    name varchar(255)    not null
);
