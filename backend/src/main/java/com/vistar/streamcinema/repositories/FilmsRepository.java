package com.vistar.streamcinema.repositories;

import com.vistar.streamcinema.entity.Films;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmsRepository extends JpaRepository<Films, Long> {
}
