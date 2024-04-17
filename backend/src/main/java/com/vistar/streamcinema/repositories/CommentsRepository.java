package com.vistar.streamcinema.repositories;

import com.vistar.streamcinema.entity.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentsRepository extends JpaRepository<Comments, Long> {
}
