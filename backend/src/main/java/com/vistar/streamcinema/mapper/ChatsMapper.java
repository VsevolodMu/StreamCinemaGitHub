package com.vistar.streamcinema.mapper;

import com.vistar.streamcinema.dto.in.ChatsInDto;
import com.vistar.streamcinema.dto.out.ChatsOutDto;
import com.vistar.streamcinema.entity.Chats;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface ChatsMapper {
    ChatsOutDto toDTO(Chats chats);

    Chats toEntity(ChatsInDto chatsOutDTO);
}
