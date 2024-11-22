package org.tspf.pffilmweb.mvc.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.tspf.pffilmweb.dto.FilmInfoDTO;
import org.tspf.pffilmweb.service.FilmService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/film")
public class FilmMvcController {


    private final FilmService filmService;

    @GetMapping("/films")
    public ModelAndView getFilms(){

        List<FilmInfoDTO> films = filmService.getFilmsInfo();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("films/films-list");
        modelAndView.addObject("films", films);

        return modelAndView;

    }

    @GetMapping("/prueba")
    public String getPrueba(){

        return "prueba";

    }


}
