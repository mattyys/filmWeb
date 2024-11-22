package org.tspf.pffilmweb.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.tspf.pffilmweb.repository.ArtistDAO;
import org.tspf.pffilmweb.repository.FilmDAO;
import org.tspf.pffilmweb.service.ArtistService;
import org.tspf.pffilmweb.service.FilmService;
import org.tspf.pffilmweb.service.impl.ArtistServiceImpl;
import org.tspf.pffilmweb.service.impl.FilmServiceImpl;

import java.util.List;

@Configuration
@Profile("test")
public class TestConfig {

  @Bean
  public FilmService filmService(FilmDAO filmDAO) {
    return new FilmServiceImpl(filmDAO);
  }

}
