package org.tspf.pffilmweb.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.tspf.pffilmweb.domain.Artist;
import org.tspf.pffilmweb.domain.Film;
import org.tspf.pffilmweb.domain.Role;
import org.tspf.pffilmweb.domain.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest(
    properties = {
      "spring.datasource.url+jdbc:h2:mem:testdb;MODE=MYSQL;DATABASE_TO_LOWER=TRUE",
      "spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.H2Dialect",
      "spring.jpa.hibernate.ddl-auto=create-drop",
    })
class FilmDAOTest {

  @Autowired private FilmDAO filmDAO;
  @Autowired private ArtistDAO artistDAO;
  @Autowired private RoleDAO roleDAO;
  @Autowired private UserDAO userDAO;

  @BeforeEach
  void beforeEach() {

    Artist juan = Artist.builder().name("Juan").surname("Perez").build();
    Artist maria = Artist.builder().name("Maria").surname("Gomez").build();
    Artist pedro = Artist.builder().name("Pedro").surname("Garcia").build();
    Artist director = Artist.builder().name("Director").surname("Director").build();

    artistDAO.saveAll(List.of(juan, maria, pedro, director));

    Role admin = Role.builder().name("ADMIN").build();
    Role user = Role.builder().name("USER").build();

    roleDAO.saveAll(List.of(admin, user));

    User user1 =
        User.builder()
            .username("user1")
            .email("user@email.com")
            .roles(new HashSet<>(roleDAO.findAll()))
            .birthDate(LocalDate.of(1990, 1, 1))
            .createdAt(LocalDateTime.now().minusDays(1))
            .surname("surname")
            .password("1234")
            .lastLoginAt(LocalDateTime.now())
            .films(Set.of())
            .build();

    Film it =
        Film.builder()
            .title("IT")
            .releaseYear(2017)
            .artists(Set.of(juan, maria, pedro))
            .director(director)
            .user(user1)
            .build();

    Film it2 =
        Film.builder()
            .title("IT2")
            .releaseYear(2019)
            .artists(Set.of(juan, pedro))
            .director(director)
            .user(user1)
            .build();

    user1.setFilms(Set.of(it, it2));

    userDAO.save(user1);
  }

  @Test
  void givenFilms_whenFindAll_thenReturnOk() {

    List<Film> films = filmDAO.findAll();

    assertEquals(2, films.size());
  }

  @Test
  void givenUser_whenFindAllFilms_thenReturnOk() {

    User user = userDAO.findByUsername("user1");

    assertEquals(2, user.getFilms().size());
    assertEquals(
        "IT",
        user.getFilms().stream()
            .filter(film -> film.getTitle().equals("IT"))
            .findFirst()
            .get()
            .getTitle());
  }
}
