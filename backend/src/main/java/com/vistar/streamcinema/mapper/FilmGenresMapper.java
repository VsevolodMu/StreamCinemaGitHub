package com.vistar.streamcinema.mapper;

import com.vistar.streamcinema.dto.in.FilmGenresInDto;
import com.vistar.streamcinema.dto.out.FilmGenresOutDto;
import com.vistar.streamcinema.entity.FilmGenres;
import com.vistar.streamcinema.entity.Films;
import com.vistar.streamcinema.entity.Genres;
import com.vistar.streamcinema.service.FilmGenresService;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING, uses = {FilmGenresService.class})
public abstract class FilmGenresMapper {
    public abstract FilmGenresOutDto toDTO(FilmGenres filmGenres);

    public abstract FilmGenres toEntity(FilmGenresInDto filmGenresOutDto);

    protected long toFilmID(Films films) {

        return films.getId();
    }

    protected long toGenreID(Genres genres) {

        return genres.getId();
    }
}
