package com.vistar.streamcinema.repositories;

import com.vistar.streamcinema.entity.RoomUsers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomUsersRepository extends JpaRepository<RoomUsers, Long> {
}
