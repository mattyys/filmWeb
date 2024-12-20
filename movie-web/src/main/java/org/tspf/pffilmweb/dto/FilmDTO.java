package org.tspf.pffilmweb.dto;


import lombok.*;
import org.tspf.pffilmweb.domain.Artist;
import org.tspf.pffilmweb.domain.FilmImage;
import org.tspf.pffilmweb.domain.User;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FilmDTO {

    private Long id;
    private String title;
    private Integer releaseYear;
    private Artist director;
    private List<Artist> artists;
    private User user;
//    private Double rating;
    private FilmImage filmImage;


}
