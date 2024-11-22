package org.tspf.pffilmweb.service.impl;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.tspf.pffilmweb.config.TestConfig;
import org.tspf.pffilmweb.domain.Artist;
import org.tspf.pffilmweb.domain.ArtistType;
import org.tspf.pffilmweb.domain.Film;
import org.tspf.pffilmweb.dto.FilmDTO;
import org.tspf.pffilmweb.dto.FilmInfoDTO;
import org.tspf.pffilmweb.repository.ArtistDAO;
import org.tspf.pffilmweb.repository.FilmDAO;
import org.tspf.pffilmweb.service.FilmService;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest(
        properties = {
                "spring.datasource.url+jdbc:h2:mem:testdb;MODE=MYSQL;DATABASE_TO_LOWER=TRUE",
                "spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.H2Dialect",
                "spring.jpa.hibernate.ddl-auto=create-drop",
        })
@Import(TestConfig.class)
@ActiveProfiles("test")
class FilmServiceImplTest {

    @Autowired FilmService filmService;
    @Autowired FilmDAO filmDAO;
    @Autowired ArtistDAO artistDAO;


    Film film1;
    Film film2;
    Film film3;

    @BeforeEach
    void beforeEach() {
        //crear artist
        Artist director = Artist.builder().name("Francis").surname("Ford Coppola").type(ArtistType.DIRECTOR).build();
        Artist marlon = Artist.builder().name("Marlon").surname("Brando").type(ArtistType.ACTOR).build();
        Artist al = Artist.builder().name("Al").surname("Pacino").type(ArtistType.ACTOR).build();
        Artist robert = Artist.builder().name("Robert").surname("De Niro").type(ArtistType.ACTOR).build();
        Artist director2 = Artist.builder().name("Steven").surname("Spielberg").type(ArtistType.DIRECTOR).build();
        Artist harrison = Artist.builder().name("Harrison").surname("Ford").type(ArtistType.ACTOR).build();
        Artist sean = Artist.builder().name("Sean").surname("Connery").type(ArtistType.ACTOR).build();

        artistDAO.saveAll(Set.of(director, marlon, al, robert, director2, harrison, sean));

        //crear 2 film
        film1 = Film.builder()
                .title("The Godfather")
                .releaseYear(1972)
                .director(director)
                .artists(Set.of(marlon,al))
                .filmImage(null)
                .build();

        film2 = Film.builder()
                .title("The Godfather II")
                .releaseYear(1974)
                .director(director)
                .artists(Set.of(marlon,al, robert))
                .filmImage(null)
                .build();

        film3 = Film.builder()
                .title("Indiana Jones")
                .releaseYear(1981)
                .director(director2)
                .artists(Set.of(harrison,sean))
                .filmImage(null)
                .build();


        filmDAO.save(film1);
        filmDAO.save(film2);
        filmDAO.save(film3);

    }

   @Test
   void givenFilms_whenFindAll_thenOk(){
       List<Film> films = filmDAO.findAll();

         assertEquals(3, films.size());

   }

    @Test
    void givenFilms_whenGetFilmsInfoConvertToFilmInfoDTO_thenOk(){

        List<FilmInfoDTO> filmsInfo = filmService.getFilmsInfo();

        assertEquals(3, filmsInfo.size());

        Assertions.assertThat(filmsInfo.get(0))
                .returns(film1.getId(), FilmInfoDTO::getId)
                .returns(film1.getTitle(), FilmInfoDTO::getTitle)
                .returns(film1.getReleaseYear(), FilmInfoDTO::getReleaseYear)
                .satisfies(f -> Assertions.assertThat(f.getFilmImage()).isNull());

    }

    @Test
    void givenFilms_whenGetFilmsInfoLike_thenOk(){
        List<FilmInfoDTO> filmsInfoLike = filmService.getFilmsInfoLike("god");

        assertEquals(2, filmsInfoLike.size());
    }

    @Test
    void givenFilm_whenGetFilmById_thenOk(){

        FilmDTO maybeFilmDTO = filmService.getFilm(3L);

        assertEquals(film3.getTitle(), maybeFilmDTO.getTitle());
        assertEquals(film3.getReleaseYear(), maybeFilmDTO.getReleaseYear());
        assertEquals(film3.getDirector().getName(), maybeFilmDTO.getDirector().getName());
    }


}
