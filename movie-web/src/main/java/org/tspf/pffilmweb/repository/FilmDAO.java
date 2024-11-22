package org.tspf.pffilmweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tspf.pffilmweb.domain.Film;

import java.util.List;

@Repository
public interface FilmDAO extends JpaRepository<Film, Long> {

  List<Film> findByTitleContainingIgnoreCase(String title);

}
