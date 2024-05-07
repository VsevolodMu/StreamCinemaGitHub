package com.vistar.streamcinema.mapper;

import com.vistar.streamcinema.dto.in.CommentsInDto;
import com.vistar.streamcinema.dto.out.CommentsOutDto;
import com.vistar.streamcinema.entity.Comments;
import com.vistar.streamcinema.entity.Films;
import com.vistar.streamcinema.entity.Users;
import com.vistar.streamcinema.service.CommentsService;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING, uses = {CommentsService.class})
public abstract class CommentsMapper {
    public abstract CommentsOutDto toDTO(Comments comments);

    public abstract Comments toEntity(CommentsInDto commentsInDto);

    protected long toFilmID(Films films) {

        return films.getId();
    }

    protected long toUserID(Users users) {

        return users.getId();
    }


}
