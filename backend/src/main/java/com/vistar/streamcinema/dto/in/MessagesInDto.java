package com.vistar.streamcinema.dto.in;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.time.Instant;

public record MessagesInDto(
        int chatID,
        int senderID,
        String messageText,
        @Temporal(TemporalType.TIMESTAMP) Instant messagesDate
) {

}