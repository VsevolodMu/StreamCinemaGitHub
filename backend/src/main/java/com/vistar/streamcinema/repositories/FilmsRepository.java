package com.vistar.streamcinema.repositories;

import com.vistar.streamcinema.entity.Films;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FilmsRepository extends JpaRepository<Films, Long> {
    List<Films> findByRate(double rate);
}
