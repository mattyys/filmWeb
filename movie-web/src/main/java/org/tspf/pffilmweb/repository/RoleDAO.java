package org.tspf.pffilmweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tspf.pffilmweb.domain.Role;

@Repository
public interface RoleDAO extends JpaRepository<Role, Long> {}
