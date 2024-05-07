package com.vistar.streamcinema.repositories;

import com.vistar.streamcinema.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {

    Optional<Users> findByUsername(String userName);
}
