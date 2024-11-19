package org.tspf.pffilmweb.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.tspf.core.converter.ModelMapperConfiguration;
import org.tspf.pffilmweb.dto.FilmDTO;
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



}
