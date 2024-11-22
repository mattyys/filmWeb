package org.tspf.pffilmweb.dto;


import lombok.*;
import org.tspf.pffilmweb.domain.ArtistType;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArtistDTO {

    private Long id;
    private String name;
    private String surname;
    private ArtistType type;

}
