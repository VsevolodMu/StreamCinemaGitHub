package com.vistar.streamcinema.repositories;

import com.vistar.streamcinema.entity.Genres;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenresRepository extends JpaRepository<Genres, Long> {
}
