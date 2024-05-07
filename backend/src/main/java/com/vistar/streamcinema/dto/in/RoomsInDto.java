package com.vistar.streamcinema.dto.in;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.time.Instant;

public record RoomsInDto(
        @Temporal(TemporalType.TIMESTAMP) Instant time,
        String description,
        int filmID,
        int creatorID) {
}
