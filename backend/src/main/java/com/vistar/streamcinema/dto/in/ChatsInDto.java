package com.vistar.streamcinema.dto.in;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.time.Instant;

public record ChatsInDto(
        String name,
        String userStatus,
        String lastMessage,
        @Temporal(TemporalType.TIMESTAMP) Instant lastMessageTime,
        @Temporal(TemporalType.TIMESTAMP) Instant chatDate) {
}