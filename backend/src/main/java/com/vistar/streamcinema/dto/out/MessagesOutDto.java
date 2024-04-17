package com.vistar.streamcinema.dto.out;

public record MessagesOutDto(
        long id,
        int chatID,
        int senderID,
        String messageText
) {

}
