package com.vistar.streamcinema.mapper;

import com.vistar.streamcinema.dto.in.RoomsInDto;
import com.vistar.streamcinema.dto.out.RoomsOutDto;
import com.vistar.streamcinema.entity.Films;
import com.vistar.streamcinema.entity.Rooms;
import com.vistar.streamcinema.entity.Users;
import com.vistar.streamcinema.service.RoomsService;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING, uses = {RoomsService.class})
public abstract class RoomsMapper {
    public abstract RoomsOutDto toDTO(Rooms rooms);

    public abstract Rooms toEntity(RoomsInDto roomsOutDto);

    protected long toFilmID(Films films) {

        return films.getId();
    }

    protected long toUserID(Users users) {

        return users.getId();
    }
}
