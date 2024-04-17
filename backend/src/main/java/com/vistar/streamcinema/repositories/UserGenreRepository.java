package com.vistar.streamcinema.repositories;

import com.vistar.streamcinema.entity.UserGenres;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserGenreRepository extends JpaRepository<UserGenres, Long> {
}
