package com.vistar.streamcinema.mapper;

import com.vistar.streamcinema.dto.in.FilmsInDto;
import com.vistar.streamcinema.dto.out.FilmsOutDto;
import com.vistar.streamcinema.entity.Films;
import com.vistar.streamcinema.entity.Users;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public abstract class FilmsMapper {

    public abstract FilmsOutDto toDTO(Films films);

    public abstract Films toEntity(FilmsInDto filmInDto);

    protected long toFilmID(Films films) {

        return films.getId();
    }

    protected long toUserID(Users users) {

        return users.getId();
    }
}
