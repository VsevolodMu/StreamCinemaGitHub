package com.vistar.streamcinema.dto.out;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.time.Instant;

public record SubscriptionsOutDto(
        long id,
        int userID,
        @Temporal(TemporalType.TIMESTAMP) Instant subscribedAt,
        @Temporal(TemporalType.TIMESTAMP) Instant subscribedUntil
) {

}
