package com.vistar.streamcinema.dto.out;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Size;

import java.time.Instant;

public record ActorsOutDto(
        long id,
        @Size(min = 1, max = 1000) String name,
        String surname,
        @Temporal(TemporalType.TIMESTAMP)
        Instant birth,
        String biography) {
}