package com.vistar.streamcinema.dto.out;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.time.Instant;

public record FriendsOutDto(
        long id,
        int requestID,
        int recipientID,
        boolean friendshipStatus,
        @Temporal(TemporalType.TIMESTAMP) Instant chatDate) {

}
