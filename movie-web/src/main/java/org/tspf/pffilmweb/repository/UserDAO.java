package org.tspf.pffilmweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tspf.pffilmweb.domain.Film;
import org.tspf.pffilmweb.domain.User;

import java.util.Set;

@Repository
public interface UserDAO extends JpaRepository<User, Long> {
    User findByUsername(String username);

    User findUserByEmail(String mail);


    User findByUsernameOrEmailAndPassword(String username, String email, String password);
}
