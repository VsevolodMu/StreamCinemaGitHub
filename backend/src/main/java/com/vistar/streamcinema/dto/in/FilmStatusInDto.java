package com.vistar.streamcinema.dto.in;

public record FilmStatusInDto(
        int userID,
        int filmID,
        boolean planned,
        boolean watched,
        boolean favorite
) {
}
