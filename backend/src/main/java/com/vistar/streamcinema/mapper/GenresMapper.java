package com.vistar.streamcinema.mapper;

import com.vistar.streamcinema.dto.in.GenresInDto;
import com.vistar.streamcinema.dto.out.GenresOutDto;
import com.vistar.streamcinema.entity.Genres;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface GenresMapper {
    GenresOutDto toDTO(Genres genres);

    Genres toEntity(GenresInDto genresInDto);
}
