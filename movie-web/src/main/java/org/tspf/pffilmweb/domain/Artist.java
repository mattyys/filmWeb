package org.tspf.pffilmweb.domain;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "artists")
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar")
    private ArtistType type;

    @ManyToOne
    @JoinColumn(name = "film_id")
    private Film film;


}
