package org.tspf.pffilmweb.service.impl;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.tspf.pffilmweb.config.TestConfig;
import org.tspf.pffilmweb.domain.Artist;
import org.tspf.pffilmweb.domain.ArtistType;
import org.tspf.pffilmweb.dto.ArtistDTO;
import org.tspf.pffilmweb.repository.ArtistDAO;
import org.tspf.pffilmweb.service.ArtistService;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest(
        properties = {
                "spring.datasource.url+jdbc:h2:mem:testdb;MODE=MYSQL;DATABASE_TO_LOWER=TRUE",
                "spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.H2Dialect",
                "spring.jpa.hibernate.ddl-auto=create-drop",
        })
@Import(ArtistServiceImplTest.TestConfig.class)
class ArtistServiceImplTest {

    @Autowired
    ArtistDAO artistDAO;

    @Autowired
    ArtistService artistService;

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

    }

    @Test
    void givenArtist_whenFindById_thenReturnOk(){
        ArtistDTO artist = artistService.getArtist(3L);

        assertEquals("Al", artist.getName());

    }

    @Test
    void givenArtists_whenFindAll_thenReturnOk(){
        List<ArtistDTO> artists = artistService.getArtists();

        assertEquals(7, artists.size());

       assertEquals("Francis", artists.get(0).getName());



    }

    @Configuration
    static class TestConfig {
        @Bean
        public ArtistService artistService(ArtistDAO artistDAO, ModelMapper modelMapper) {
            return new ArtistServiceImpl(artistDAO, modelMapper);
        }

        @Bean
        public ModelMapper modelMapper() {
            return new ModelMapper();
        }
        @Bean
        public DataSource dataSource() {
            return new org.springframework.jdbc.datasource.DriverManagerDataSource("jdbc:h2:mem:testdb;MODE=MYSQL;DATABASE_TO_LOWER=TRUE");
        }
    }


}
