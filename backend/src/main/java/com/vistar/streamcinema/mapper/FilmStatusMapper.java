package com.vistar.streamcinema.mapper;

import com.vistar.streamcinema.dto.in.FilmStatusInDto;
import com.vistar.streamcinema.dto.out.FilmStatusOutDto;
import com.vistar.streamcinema.entity.FilmStatus;
import com.vistar.streamcinema.entity.Films;
import com.vistar.streamcinema.entity.Users;
import com.vistar.streamcinema.service.FilmStatusService;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING, uses = {FilmStatusService.class})
public abstract class FilmStatusMapper {
    public abstract FilmStatusOutDto toDTO(FilmStatus filmStatus);

    public abstract FilmStatus toEntity(FilmStatusInDto filmStatusOutDto);

    protected long toFilmID(Films films) {

        return films.getId();
    }

    protected long toUserID(Users users) {

        return users.getId();
    }
}
