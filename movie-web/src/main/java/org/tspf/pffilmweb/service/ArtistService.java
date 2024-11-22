package org.tspf.pffilmweb.service;

import org.tspf.pffilmweb.dto.ArtistDTO;
import org.tspf.pffilmweb.mvc.dto.ArtistMvcDTO;

import java.util.List;

public interface ArtistService {

  ArtistDTO getArtist(Long artistId);

  List<ArtistDTO> getArtists();

  ArtistDTO createArtist(ArtistMvcDTO artistMvcDTO);

  ArtistDTO editArtist(ArtistMvcDTO artistMvcDTO);

}
