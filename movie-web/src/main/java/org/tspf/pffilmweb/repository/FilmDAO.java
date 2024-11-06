package org.tspf.pffilmweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tspf.pffilmweb.domain.Film;

@Repository
public interface FilmDAO extends JpaRepository<Film, Long> {


}
