package com.vistar.streamcinema.repositories;

import com.vistar.streamcinema.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Long> {
}
