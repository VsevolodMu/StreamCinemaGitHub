package com.vistar.streamcinema.dto.in;

public record CommentsInDto(
        int filmID,
        int userID,
        String commentText) {
}
