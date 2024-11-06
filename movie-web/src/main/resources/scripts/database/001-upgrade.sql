drop database filmdb;
create database filmdb;
use filmdb;

create table if not exists users
(
    id            bigint(20) unsigned primary key auto_increment not null,
    created_at    datetime                                       not null,
    username      varchar(255)                                   not null,
    email         varchar(255)                                   not null,
    name          varchar(255)                                   not null,
    surname       varchar(255)                                   not null,
    password      varchar(255)                                   not null,
    birth_date    date                                           not null,
    last_login_at datetime                                       not null

);
create table if not exists artists
(
    id      bigint(20) unsigned primary key auto_increment not null,
    name    varchar(255)                                   not null,
    surname varchar(255)                                   not null,
    type    varchar(255)                                   not null
);
create table if not exists films
(
    id           bigint(20) unsigned primary key auto_increment not null,
    title        varchar(255)                                   not null,
    release_year int                                            not null,
    poster       varchar(255)                                   not null,
    director_id  bigint(20) unsigned                            not null references artists (id),
    user_id      bigint(20) unsigned                            not null references users (id)
);

create table if not exists roles
(
    id   bigint(20) unsigned not null auto_increment primary key,
    name varchar(255)        not null
);

create table users_with_roles
(
    user_id bigint(20) unsigned not null references users (id),
    role_id bigint(20) unsigned not null references roles (id)
);



create table if not exists film_performed_by_artist
(
    film_id   bigint(20) unsigned not null references films (id),
    artist_id bigint(20) unsigned not null references artists (id)
);

create table if not exists film_images
(
    id           bigint(20) unsigned primary key auto_increment not null,
    filename     varchar(255)                                   not null,
    size         int                                            not null,
    content_type varchar(255)                                   not null,
    resource_id  varchar(36)                                    not null,
    film_id      bigint(20) unsigned                            not null references films (id)
);
ALTER TABLE artists ADD COLUMN film_id BIGINT;



