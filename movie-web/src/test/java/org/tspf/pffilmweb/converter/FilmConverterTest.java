package org.tspf.pffilmweb.converter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.tspf.pffilmweb.core.converter.ModelMapperConfiguration;
import org.tspf.pffilmweb.domain.Artist;
import org.tspf.pffilmweb.domain.Film;
import org.tspf.pffilmweb.domain.User;
import org.tspf.pffilmweb.dto.FilmDTO;

import java.time.LocalDateTime;
import java.util.Set;

public class FilmConverterTest {

    private final ModelMapper modelMapper = new ModelMapperConfiguration().modelMapper();

    @Test
    void givenFilm_whenConvertToFilmDTO_thenOk(){

        User user = User.builder()
                .id(1L)
                .username("user")
                .password("password")
                .email("user@email.com")
                .surname("surname")
                .films(Set.of())
                .createdAt(LocalDateTime.now())
                .roles(Set.of())
                .build();

        Film film = Film.builder()
                .id(1L)
                .title("The Godfather")
                .user(user)
                .artists(Set.of(
                        Artist.builder().id(1L).name("Marlon").surname("Brando").build(),
                        Artist.builder().id(2L).name("Al").surname("Pacino").build()
                ))
                .director(Artist.builder().id(1L).name("Francis").surname("Ford Coppola").build())
                .releaseYear(1972)
                .filmImage(null)
                .build();

        FilmDTO filmDTO = modelMapper.map(film, FilmDTO.class);

        Assertions.assertThat(filmDTO)
                .returns(film.getId(), FilmDTO::getId)
                .returns(film.getTitle(), FilmDTO::getTitle)
                .returns(film.getReleaseYear(), FilmDTO::getReleaseYear)
                .returns(film.getDirector(), FilmDTO::getDirector)
                .returns(film.getArtists().size(), f -> f.getArtists().size())
                .returns(film.getUser(), FilmDTO::getUser)
                .satisfies(f -> Assertions.assertThat(f.getFilmImage()).isNull());

    }




}
