package com.vistar.streamcinema.mapper;

import com.vistar.streamcinema.dto.in.UserGenresInDto;
import com.vistar.streamcinema.dto.out.UserGenresOutDto;
import com.vistar.streamcinema.entity.Genres;
import com.vistar.streamcinema.entity.UserGenres;
import com.vistar.streamcinema.entity.Users;
import com.vistar.streamcinema.service.UserGenresService;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING, uses = {UserGenresService.class})
public abstract class UserGenresMapper {
    public abstract UserGenresOutDto toDTO(UserGenres userGenres);

    public abstract UserGenres toEntity(UserGenresInDto userGenresOutDto);

    protected long toGenreID(Genres genres) {

        return genres.getId();
    }

    protected long toUserID(Users users) {

        return users.getId();
    }

}
