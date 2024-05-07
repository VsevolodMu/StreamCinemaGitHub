package com.vistar.streamcinema.mapper;

import com.vistar.streamcinema.dto.in.RoomUsersInDto;
import com.vistar.streamcinema.dto.out.RoomUsersOutDto;
import com.vistar.streamcinema.entity.RoomUsers;
import com.vistar.streamcinema.entity.Rooms;
import com.vistar.streamcinema.entity.Users;
import com.vistar.streamcinema.service.RoomUsersService;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING, uses = {RoomUsersService.class})
public abstract class RoomUsersMapper {
    public abstract RoomUsersOutDto toDTO(RoomUsers roomUsers);

    public abstract RoomUsers toEntity(RoomUsersInDto roomUsersOutDto);

    protected long toUserID(Users users) {

        return users.getId();
    }

    protected long toRoomID(Rooms rooms) {

        return rooms.getId();
    }
}
