package org.tspf.pffilmweb.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {

    @Id
    private Long id;
    private LocalDateTime createdAt;
    private String username;
    private String email;
    private String surname;
    private String password;
    private LocalDate birthDate;
    private LocalDateTime lastLoginAt;

    @ManyToMany
    private Set<Role> roles;

    @OneToMany
    private Set<Film> films;

}
