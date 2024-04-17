package com.vistar.streamcinema.repositories;

import com.vistar.streamcinema.entity.FilmGenres;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmGenreRepository extends JpaRepository<FilmGenres, Long> {
}
