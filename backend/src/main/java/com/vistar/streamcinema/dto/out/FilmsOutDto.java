package com.vistar.streamcinema.dto.out;

import jakarta.validation.Valid;

import java.util.Set;

public record FilmsOutDto(
        long id,
        String title,
        String description,
        double rate,
        String path,
        @Valid
        Set<CommentsOutDto> commentsList
) {
}
