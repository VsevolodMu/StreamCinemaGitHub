package com.vistar.streamcinema.repositories;

import com.vistar.streamcinema.entity.UserActors;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserActorRepository extends JpaRepository<UserActors, Long> {
}
