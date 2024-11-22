package org.tspf.pffilmweb.repository;

import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.tspf.pffilmweb.domain.Artist;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest(properties = {
        "spring.datasource.url+jdbc:h2:mem:testdb;MODE=MYSQL;DATABASE_TO_LOWER=TRUE",
        "spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.H2Dialect",
        "spring.jpa.hibernate.ddl-auto=create-drop",
})
class ArtistDAOTest {

    @Autowired private ArtistDAO artistDAO;

    @BeforeEach
    void beforeEach(){
        Artist juan = Artist.builder().name("Juan").surname("Perez").build();
        Artist maria = Artist.builder().name("Maria").surname("Gomez").build();
        Artist pedro = Artist.builder().name("Pedro").surname("Garcia").build();
        Artist director = Artist.builder().name("Director").surname("Director").build();

        artistDAO.saveAll(List.of(juan, maria, pedro, director));
    }

    @Test
    void givenArtists_whenFindAll_thenReturnOk(){

        List<Artist> artists = artistDAO.findAll();

        Assertions.assertEquals(4, artists.size());
    }

  @Test
  void givenArtist_whenFindByName_thenReturnOk() {

    Artist maybeJuan = artistDAO.findByName("Juan");

    Assertions.assertEquals("Juan", maybeJuan.getName());
}

@Test
    void givenArtist_whenFindByNameContainingOrSurnameContaining_thenReturnOk() {

        Artist maybeJuan = artistDAO.findByNameContainingOrSurnameContaining("Ju", null);

        Assertions.assertEquals("Perez", maybeJuan.getSurname());
    }



}
