package com.vistar.streamcinema.dto.in;

public record FriendsInDto(
        int requestID,
        int recipientID,
        boolean friendshipStatus
) {

}
