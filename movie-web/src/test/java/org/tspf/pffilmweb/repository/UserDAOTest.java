package org.tspf.pffilmweb.repository;

import org.antlr.v4.runtime.tree.pattern.ParseTreePattern;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.tspf.pffilmweb.domain.Artist;
import org.tspf.pffilmweb.domain.Film;
import org.tspf.pffilmweb.domain.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest(
    properties = {
      "spring.datasource.url+jdbc:h2:mem:testdb;MODE=MYSQL;DATABASE_TO_LOWER=TRUE",
      "spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.H2Dialect",
      "spring.jpa.hibernate.ddl-auto=create-drop",
    })
class UserDAOTest {

  @Autowired private UserDAO userDAO;
  @Autowired private FilmDAO filmDAO;
  @Autowired private RoleDAO roleDAO;
  @Autowired private ArtistDAO artistDAO;

  @BeforeEach
  void beforeEach() {
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

    User user2 =
            User.builder()
                    .username("user2")
                    .email("user2@email.com")
                    .roles(new HashSet<>(roleDAO.findAll()))
                    .birthDate(LocalDate.of(1985, 1, 1))
                    .createdAt(LocalDateTime.now().minusDays(20))
                    .surname("surname2")
                    .password("1234")
                    .lastLoginAt(LocalDateTime.now())
                    .films(Set.of())
                    .build();

    Artist juan = Artist.builder().name("Juan").surname("Perez").build();
    Artist maria = Artist.builder().name("Maria").surname("Gomez").build();
    Artist pedro = Artist.builder().name("Pedro").surname("Garcia").build();
    Artist director = Artist.builder().name("Director").surname("Director").build();

    artistDAO.saveAll(List.of(juan, maria, pedro, director));

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

    Film elResplandor = Film.builder()
            .title("El Resplandor")
            .releaseYear(1980)
            .artists(Set.of(juan, maria, pedro))
            .director(director)
            .user(user2)
            .build();

    user1.setFilms(Set.of(it, it2));
    userDAO.saveAll(List.of(user1, user2));

  }

  @Test
  void givenUsers_whenFindAll_thenReturnOk() {
    List<User> users = userDAO.findAll();
    assertEquals(2, users.size());
  }

  @Test
  void givenUser_whenFindByUsername_thenReturnOk() {
    User user = userDAO.findByUsername("user1");
    assertEquals("user1", user.getUsername());
  }

  @Test
  void givenUser_whenFindByEmail_thenReturnOk() {
    User user = userDAO.findUserByEmail("user2@email.com");

    assertEquals("user2", user.getUsername());
  }

  @Test
  void givenUser_whenFindByUsernameOrEmailAndPassword_thenReturnOk() {

    User user = userDAO.findByUsernameOrEmailAndPassword("user1",null, "1234");
    assertEquals("user1", user.getUsername());

    User user2 = userDAO.findByUsernameOrEmailAndPassword(null,"user2@email.com","1234");
    assertEquals("user2", user2.getUsername());

  }

}
