package org.tspf.pffilmweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tspf.pffilmweb.domain.Artist;


@Repository
public interface ArtistDAO extends JpaRepository<Artist, Long> {

    Artist findByName(String name);

    Artist findByNameContainingOrSurnameContaining(String name, String surname);
}
