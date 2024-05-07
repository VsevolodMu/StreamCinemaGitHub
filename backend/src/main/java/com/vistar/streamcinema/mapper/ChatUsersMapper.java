package com.vistar.streamcinema.mapper;

import com.vistar.streamcinema.dto.in.ChatUsersInDto;
import com.vistar.streamcinema.dto.out.ChatUsersOutDto;
import com.vistar.streamcinema.entity.ChatUsers;
import com.vistar.streamcinema.entity.Chats;
import com.vistar.streamcinema.entity.Users;
import com.vistar.streamcinema.service.ChatUsersService;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING, uses = {ChatUsersService.class})
public abstract class ChatUsersMapper {
    public abstract ChatUsersOutDto toDTO(ChatUsers chatUsers);

    public abstract ChatUsers toEntity(ChatUsersInDto chatUsersInDto);

    protected long toChatID(Chats chats) {

        return chats.getId();
    }

    protected long toUserID(Users users) {

        return users.getId();
    }
}
