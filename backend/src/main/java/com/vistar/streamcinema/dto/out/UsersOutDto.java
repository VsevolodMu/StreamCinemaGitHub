package com.vistar.streamcinema.dto.out;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.time.Instant;

public record UsersOutDto(
        long id,
        String username,
        String password,
        String email,
        @Temporal(TemporalType.TIMESTAMP) Instant registrationDate,
        @Temporal(TemporalType.TIMESTAMP) Instant lastLoginDate,
        int favoriteGenreID,
        String profileInfo,
        String number,
        @Temporal(TemporalType.TIMESTAMP) Instant birth
) {


}
