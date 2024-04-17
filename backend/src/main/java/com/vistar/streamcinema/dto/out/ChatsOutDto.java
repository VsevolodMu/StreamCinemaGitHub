package com.vistar.streamcinema.dto.out;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.time.Instant;

public record ChatsOutDto(
        long id,
        String name,
        String userStatus,
        String lastMessage,
        @Temporal(TemporalType.TIMESTAMP) Instant lastMessageTime
) {
}
