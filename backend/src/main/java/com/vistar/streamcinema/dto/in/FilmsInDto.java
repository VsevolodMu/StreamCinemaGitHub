package com.vistar.streamcinema.dto.in;

public record FilmsInDto(
        String title,
        String description,
        double rate,
        String cover,
        String path
) {
}
