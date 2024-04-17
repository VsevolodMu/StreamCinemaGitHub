package com.vistar.streamcinema.mapper;

import com.vistar.streamcinema.dto.in.FilmsInDto;
import com.vistar.streamcinema.dto.out.CommentsOutDto;
import com.vistar.streamcinema.dto.out.FilmsOutDto;
import com.vistar.streamcinema.entity.Comments;
import com.vistar.streamcinema.entity.Films;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public abstract class FilmsMapper {
    @Autowired
    private CommentsMapper commentsMapper;

    public abstract FilmsOutDto toDTO(Films films);

    public abstract Films toEntity(FilmsInDto filmInDto);

    // used by mapper
    protected List<CommentsOutDto> entitiesToDtos(List<Comments> comments) {
        if (comments == null) {
            return List.of();
        }
        return comments.stream().map(commentsMapper::toDTO).toList();
    }
}
