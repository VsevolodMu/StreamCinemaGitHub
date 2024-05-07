package com.vistar.streamcinema.mapper;

import com.vistar.streamcinema.dto.in.FilmActorsInDto;
import com.vistar.streamcinema.dto.out.FilmActorsOutDto;
import com.vistar.streamcinema.entity.Actors;
import com.vistar.streamcinema.entity.FilmActors;
import com.vistar.streamcinema.entity.Films;
import com.vistar.streamcinema.service.FilmActorsService;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING, uses = {FilmActorsService.class})
public abstract class FilmActorsMapper {
    public abstract FilmActorsOutDto toDTO(FilmActors filmActors);

    public abstract FilmActors toEntity(FilmActorsInDto filmActorsOutDto);

    protected long toFilmID(Films films) {

        return films.getId();
    }

    protected long toActorID(Actors actors) {

        return actors.getId();
    }
}
