package com.vistar.streamcinema.repositories;

import com.vistar.streamcinema.entity.Messages;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessagesRepository extends JpaRepository<Messages, Long> {
}
