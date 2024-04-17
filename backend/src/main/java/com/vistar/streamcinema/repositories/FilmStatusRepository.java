package com.vistar.streamcinema.repositories;

import com.vistar.streamcinema.entity.FilmStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmStatusRepository extends JpaRepository<FilmStatus, Long> {
}
