package com.vistar.streamcinema.mapper;

import com.vistar.streamcinema.dto.in.UsersInDto;
import com.vistar.streamcinema.dto.out.UsersOutDto;
import com.vistar.streamcinema.entity.Users;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;


@Mapper(componentModel = SPRING)
public abstract class UsersMapper {
    public abstract UsersOutDto toDTO(Users users);

    public abstract Users toEntity(UsersInDto usersInDto);
}