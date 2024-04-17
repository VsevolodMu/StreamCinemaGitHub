package com.vistar.streamcinema.repositories;

import com.vistar.streamcinema.entity.ChatUsers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatUsersRepository extends JpaRepository<ChatUsers, Long> {
}
