package com.vistar.streamcinema.dto.out;

public record CommentsOutDto(
        long id,
        int filmID,
        int userID,
        String commentText) {
}
