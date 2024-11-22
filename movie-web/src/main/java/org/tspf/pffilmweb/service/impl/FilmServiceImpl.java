package org.tspf.pffilmweb.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.tspf.pffilmweb.core.converter.ModelMapperConfiguration;
import org.tspf.pffilmweb.dto.FilmDTO;
import org.tspf.pffilmweb.dto.FilmInfoDTO;
import org.tspf.pffilmweb.repository.FilmDAO;
import org.tspf.pffilmweb.service.FilmService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FilmServiceImpl implements FilmService {


    private final FilmDAO filmDAO;
    private final ModelMapper modelMapper = new ModelMapperConfiguration().modelMapper();


    @Override
    public List<FilmDTO> getFilms() {
        return filmDAO.findAll().stream()
                .map(film -> modelMapper.map(film, FilmDTO.class))
                .toList();
    }

    @Override
    public List<FilmInfoDTO> getFilmsInfo() {
        return filmDAO.findAll().stream()
                .map(film -> modelMapper.map(film, FilmInfoDTO.class))
                .toList();
    }

    @Override
    public List<FilmInfoDTO> getFilmsInfoLike(String title) {
        return filmDAO.findByTitleContainingIgnoreCase(title).stream()
                .map(film -> modelMapper.map(film, FilmInfoDTO.class))
                .toList();
    }

    @Override
    public FilmDTO getFilm(Long filmId) {
        return filmDAO
                .findById(filmId)
                .map(film -> modelMapper.map(film, FilmDTO.class))
                .orElseThrow(
                        () -> new IllegalArgumentException("Film with id:%s not found".formatted(filmId)));
    }


}
