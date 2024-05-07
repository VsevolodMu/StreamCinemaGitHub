package com.vistar.streamcinema.mapper;

import com.vistar.streamcinema.dto.in.FriendsInDto;
import com.vistar.streamcinema.dto.out.FriendsOutDto;
import com.vistar.streamcinema.entity.Friends;
import com.vistar.streamcinema.entity.Users;
import com.vistar.streamcinema.service.FriendsService;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING, uses = {FriendsService.class})
public abstract class FriendsMapper {
    public abstract FriendsOutDto toDTO(Friends friends);

    public abstract Friends toEntity(FriendsInDto friendsInDto);

    protected long toUserID(Users users) {

        return users.getId();
    }
}
