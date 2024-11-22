package org.tspf.pffilmweb.dto;

import lombok.*;
import org.tspf.pffilmweb.domain.FilmImage;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FilmInfoDTO {
    //clase para mostrar las peliculas en la pagina principal

    private Long id;
    private String title;
    private Integer releaseYear;
    private FilmImage filmImage;
}
