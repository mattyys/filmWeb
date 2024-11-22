package org.tspf.pffilmweb.service;

import org.tspf.pffilmweb.dto.FilmDTO;
import org.tspf.pffilmweb.dto.FilmInfoDTO;

import java.util.List;

public interface FilmService {

  List<FilmDTO> getFilms();

  List<FilmInfoDTO> getFilmsInfo();

  List<FilmInfoDTO> getFilmsInfoLike(String title);

  FilmDTO getFilm(Long filmId);


}
