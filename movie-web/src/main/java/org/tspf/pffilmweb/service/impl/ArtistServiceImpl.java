package org.tspf.pffilmweb.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tspf.pffilmweb.domain.Artist;
import org.tspf.pffilmweb.domain.ArtistType;
import org.tspf.pffilmweb.dto.ArtistDTO;
import org.tspf.pffilmweb.mvc.dto.ArtistMvcDTO;
import org.tspf.pffilmweb.repository.ArtistDAO;
import org.tspf.pffilmweb.service.ArtistService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArtistServiceImpl implements ArtistService {

  private final ArtistDAO artistDAO;
  private final ModelMapper modelMapper;

  @Override
  public ArtistDTO getArtist(Long artistId) {
    return artistDAO
        .findById(artistId)
        .map(artist -> modelMapper.map(artist, ArtistDTO.class))
        .orElseThrow(
            () -> new IllegalArgumentException("Artist with id:%s not found".formatted(artistId)));
  }

  @Override
  public List<ArtistDTO> getArtists() {
    return artistDAO.findAll().stream()
        .map(artist -> modelMapper.map(artist, ArtistDTO.class))
        .toList();
  }

  @Override
  @Transactional
  public ArtistDTO createArtist(ArtistMvcDTO artistMvcDTO) {

    Artist artist = createOrEditArtist(new Artist(), artistMvcDTO);

    return modelMapper.map(artist, ArtistDTO.class);
  }

  @Override
  public ArtistDTO editArtist(ArtistMvcDTO artistMvcDTO) {

    Artist artist =
        artistDAO
            .findById(artistMvcDTO.getId())
            .orElseThrow(
                () ->
                    new IllegalArgumentException(
                        "Artist with id:%s not found".formatted(artistMvcDTO.getId())));

    artist = createOrEditArtist(artist, artistMvcDTO);

    return modelMapper.map(artist, ArtistDTO.class);
  }

  protected Artist createOrEditArtist(Artist artist, ArtistMvcDTO artistMvcDTO) {

    artist.setName(artistMvcDTO.getName());
    artist.setSurname(artistMvcDTO.getSurname());
    artist.setType(ArtistType.valueOf(artistMvcDTO.getType()));

    return artistDAO.save(artist);
  }
}
