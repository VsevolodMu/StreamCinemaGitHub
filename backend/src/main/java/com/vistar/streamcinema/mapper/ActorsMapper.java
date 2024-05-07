package com.vistar.streamcinema.mapper;

import com.vistar.streamcinema.dto.in.ActorsInDto;
import com.vistar.streamcinema.dto.out.ActorsOutDto;
import com.vistar.streamcinema.entity.Actors;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface ActorsMapper {
    ActorsOutDto toDTO(Actors actors);

    Actors toEntity(ActorsInDto actorsInDto);
    /*@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Actors partialUpdate(ActorsDTO actorsDTO, @MappingTarget Actors actors);
     */
}
