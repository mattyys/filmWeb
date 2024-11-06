package org.tspf.pffilmweb.domain;


import jakarta.persistence.*;
import lombok.*;


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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Integer releaseYear;

    @OneToOne(mappedBy = "film", cascade = CascadeType.ALL, orphanRemoval = true)
    private FilmImage filmImage;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="film_performed_by_artist",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "artist_id"))
    private Set<Artist> artists;

    @ManyToOne
    @JoinColumn(name = "director_id")
    private Artist director;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
