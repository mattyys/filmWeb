package org.tspf.pffilmweb.mvc.dto;

import lombok.*;
import org.tspf.pffilmweb.domain.ArtistType;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArtistMvcDTO {

    private Long id;
    private String name;
    private String surname;
    private String type;


}
