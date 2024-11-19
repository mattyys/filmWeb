#Completar la tabla Artist con 5 registros

INSERT INTO artists (name, surname, type)
VALUES ('Brad', 'Pitt', 'ACTOR'),
       ('Margot', 'Robbie', 'ACTOR'),
       ('Michael', 'Fassbender', 'ACTOR'),
       ('Quentin', 'Tarantino', 'DIRECTOR'),
       ('Leonardo', 'DiCaprio', 'ACTOR'),
       ('John', 'Travolta', 'ACTOR'),
       ('Samuel', 'L. Jackson', 'ACTOR'),
       ('Phyllida', 'Lloyd', 'DIRECTOR'),
       ('Meryl', 'Streep', 'ACTOR'),
       ('Anne', 'Hathaway', 'ACTOR'),
       ('David', 'Frankel', 'DIRECTOR');

#Aregistros de Films

INSERT INTO films (title, release_year, poster, director_id, user_id)
values ('Pulp Fiction', 1994, 'https://www.imdb.com/title/tt0110912/mediaviewer/rm1904266240/', 3, 1),
       ('Inglorious Bastards', 2009, 'https://www.imdb.com/title/tt0361748/mediaviewer/rm1843630337/', 3, 2),
       ('Once Upon a Time in Hollywood', 2019, 'https://www.imdb.com/title/tt7131622/mediaviewer/rm3664026625/', 3, 1),
       ('The Devil Wears Prada', 2006, 'https://www.imdb.com/title/tt0458352/mediaviewer/rm1431456768/', 11, 2),
       ('The Iron Lady', 2011, 'https://www.imdb.com/title/tt1007029/mediaviewer/rm552207360/', 7, 1);

#Agregar realciones de peliculas con artistas

INSERT INTO film_performed_by_artist (film_id, artist_id) VALUES
                                                              (11, 6),
                                                              (11, 7),
                                                              (12, 1),
                                                              (12, 3),
                                                              (13, 1),
                                                              (13, 5),
                                                              (13, 3),
                                                              (14, 9),
                                                              (14, 10),
                                                              (15, 9);
