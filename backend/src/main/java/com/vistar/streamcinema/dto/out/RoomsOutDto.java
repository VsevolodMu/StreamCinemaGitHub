package com.vistar.streamcinema.dto.out;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.time.Instant;

public record RoomsOutDto(
        long id,
        @Temporal(TemporalType.TIMESTAMP) Instant time,
        String description,
        int filmID,
        int creatorID
) {
}
