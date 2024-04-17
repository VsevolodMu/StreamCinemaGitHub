package com.vistar.streamcinema.repositories;

import com.vistar.streamcinema.entity.Chats;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatsRepository extends JpaRepository<Chats, Long> {
}
