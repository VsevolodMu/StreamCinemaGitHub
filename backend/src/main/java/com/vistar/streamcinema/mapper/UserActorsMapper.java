package com.vistar.streamcinema.mapper;

import com.vistar.streamcinema.dto.in.UserActorsInDto;
import com.vistar.streamcinema.dto.out.UserActorsOutDto;
import com.vistar.streamcinema.entity.Actors;
import com.vistar.streamcinema.entity.UserActors;
import com.vistar.streamcinema.entity.Users;
import com.vistar.streamcinema.service.UserActorsService;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING, uses = {UserActorsService.class})
public abstract class UserActorsMapper {
    public abstract UserActorsOutDto toDTO(UserActors userActors);

    public abstract UserActors toEntity(UserActorsInDto userActorsOutDto);

    protected long toActorID(Actors actors) {

        return actors.getId();
    }

    protected long toUserID(Users users) {

        return users.getId();
    }
}
