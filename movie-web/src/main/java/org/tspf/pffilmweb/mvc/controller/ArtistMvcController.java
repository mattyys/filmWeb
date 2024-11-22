package org.tspf.pffilmweb.mvc.controller;

import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.tspf.pffilmweb.dto.ArtistDTO;
import org.tspf.pffilmweb.mvc.dto.ArtistMvcDTO;
import org.tspf.pffilmweb.service.ArtistService;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/artists")
public class ArtistMvcController {

  private final ArtistService artistService;

  @GetMapping({"/artist-edit", "/artist-edit/{artistId}"})
  public ModelAndView createOrEditArtist(
      @PathVariable(name = "artistId", required = false) Long artistId, Model model) {

    Optional<ArtistDTO> maybeArtistDTO =
        Optional.ofNullable(artistId).map(artistService::getArtist);

    ArtistMvcDTO artistMvcDTO =
        maybeArtistDTO
            .map(
                artistDTO ->
                    ArtistMvcDTO.builder()
                        .id(artistDTO.getId())
                        .name(artistDTO.getName())
                        .surname(artistDTO.getSurname())
                        .type(artistDTO.getType().name())
                        .build())
            .orElseGet(ArtistMvcDTO::new);

    ModelAndView modelAndView = populateCreateOrEditArtist(artistMvcDTO, maybeArtistDTO.orElse(null), model);
    modelAndView.setViewName("artists/artist-edit");

    return modelAndView;
  }

  @PostMapping({"/artist-edit","/artist-edit/", "/artist-edit/{artistId}"})
  public RedirectView createOrEditArtist(
          @ModelAttribute("artist") ArtistMvcDTO artistMvcDTO,
          @PathVariable(name = "artistId", required = false) Long artistId){

    Optional.ofNullable(artistMvcDTO.getId())
            .map(a -> artistService.editArtist(artistMvcDTO))
            .orElseGet( () ->  artistService.createArtist(artistMvcDTO));

    return new RedirectView("/artist-edit");

  }

  private ModelAndView populateCreateOrEditArtist(
      ArtistMvcDTO artistMvcDTO, @Nullable ArtistDTO artistDTO, Model model) {

    ModelAndView modelAndView = new ModelAndView();
    modelAndView.addAllObjects(model.asMap());
    modelAndView.addObject("artist", artistMvcDTO);

    return modelAndView;
  }

}
