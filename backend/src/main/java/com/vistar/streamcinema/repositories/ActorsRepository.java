package com.vistar.streamcinema.repositories;

import com.vistar.streamcinema.entity.Actors;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorsRepository extends JpaRepository<Actors, Long> {
}
