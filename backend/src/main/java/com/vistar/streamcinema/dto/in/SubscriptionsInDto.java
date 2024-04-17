package com.vistar.streamcinema.dto.in;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.time.Instant;

public record SubscriptionsInDto(
        int userID,
        @Temporal(TemporalType.TIMESTAMP) Instant subscribedAt,
        @Temporal(TemporalType.TIMESTAMP) Instant subscribedUntil
) {

}
