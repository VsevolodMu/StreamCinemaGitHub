package com.vistar.streamcinema.mapper;

import com.vistar.streamcinema.dto.in.MessagesInDto;
import com.vistar.streamcinema.dto.out.MessagesOutDto;
import com.vistar.streamcinema.entity.Chats;
import com.vistar.streamcinema.entity.Messages;
import com.vistar.streamcinema.entity.Users;
import com.vistar.streamcinema.service.MessagesService;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING, uses = {MessagesService.class})
public abstract class MessagesMapper {
    public abstract MessagesOutDto toDTO(Messages messages);

    public abstract Messages toEntity(MessagesInDto messagesOutDto);

    protected long toChatID(Chats chats) {

        return chats.getId();
    }

    protected long toUserID(Users users) {

        return users.getId();
    }
}
