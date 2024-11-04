package org.tspf.pffilmweb.domain;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "films")
public class Film {

    @Id
    private Long id;
    private String title;
    private Integer releaseYear;
    private String poster;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Artist> artists;

    @ManyToOne
    private Artist director;
}
