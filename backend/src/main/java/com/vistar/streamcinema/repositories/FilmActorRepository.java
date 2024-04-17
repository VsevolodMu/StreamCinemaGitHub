package com.vistar.streamcinema.repositories;

import com.vistar.streamcinema.entity.FilmActors;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmActorRepository extends JpaRepository<FilmActors, Long> {
}
