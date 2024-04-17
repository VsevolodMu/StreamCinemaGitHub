package com.vistar.streamcinema.dto.out;

public record FilmStatusOutDto(
        long id,
        int userID,
        int filmID,
        boolean planned,
        boolean watched,
        boolean favorite
) {
}
