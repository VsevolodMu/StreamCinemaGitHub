package com.vistar.streamcinema.controller;

import com.vistar.streamcinema.dto.in.CommentsInDto;
import com.vistar.streamcinema.dto.out.CommentsOutDto;
import com.vistar.streamcinema.entity.Comments;
import com.vistar.streamcinema.exception_handler.NotFoundException;
import com.vistar.streamcinema.mapper.CommentsMapper;
import com.vistar.streamcinema.service.CommentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class CommentsController {
    private final CommentsService commentsService;
    private final CommentsMapper commentsMapper;

    @GetMapping("/comments")
    public List<CommentsOutDto> showAllUsers() {
        List<Comments> allComments = commentsService.getAllComments();
        return allComments.stream().map(commentsMapper::toDTO).collect(Collectors.toList());
    }

    @GetMapping("/comments/{id}")
    public CommentsOutDto showComment(@PathVariable long id) {
        Comments comments = commentsService.getComment(id);
        if (comments == null) {
            throw new NotFoundException("Неверно уканазанный идентификатор. Комментарий с id = " + id + " не найден в базе данных.");
        }

        return commentsMapper.toDTO(comments);
    }

    @PostMapping("/comments")
    @ResponseStatus(code = HttpStatus.CREATED)
    public CommentsOutDto addComment(@RequestBody CommentsInDto commentsInDto) {
        Comments comments = commentsMapper.toEntity(commentsInDto);
        comments = commentsService.saveComments(comments);

        return commentsMapper.toDTO(comments);
    }

    @PutMapping("/comments/{id}")
    @ResponseStatus(code = HttpStatus.CREATED)
    public CommentsOutDto updateComment(@PathVariable long id, @RequestBody CommentsInDto commentsInDto) {
        Comments comments = commentsMapper.toEntity(commentsInDto);
        comments = commentsService.updateComment(id, comments);

        return commentsMapper.toDTO(comments);
    }

    @DeleteMapping("/comments/{id}")
    public String deleteComment(@PathVariable long id) {
        commentsService.deleteComment(id);
        return "\"information\": Комментарий с id = " + id + " больше не существует в базе данных.";
    }

}
