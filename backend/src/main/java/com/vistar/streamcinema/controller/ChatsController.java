package com.vistar.streamcinema.controller;

import com.vistar.streamcinema.dto.in.ChatsInDto;
import com.vistar.streamcinema.dto.out.ChatsOutDto;
import com.vistar.streamcinema.entity.Chats;
import com.vistar.streamcinema.exception_handler.NotFoundException;
import com.vistar.streamcinema.mapper.ChatsMapper;
import com.vistar.streamcinema.service.ChatsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class ChatsController {
    private final ChatsService chatsService;
    private final ChatsMapper chatsMapper;

    @GetMapping("/chats")
    public List<ChatsOutDto> showAllChats() {
        List<Chats> allChats = chatsService.getAllChats();

        return allChats.stream().map(chatsMapper::toDTO).collect(Collectors.toList());
    }

    @GetMapping("/chats/{id}")
    public ChatsOutDto showChat(@PathVariable int id) {
        Chats chats = chatsService.getChat(id);
        if (chats == null) {
            throw new NotFoundException("Неверно уканазанный идентификатор. Чат с id = " + id + " не найден в базе данных.");
        }

        return chatsMapper.toDTO(chats);
    }

    @PostMapping("/chats")
    @ResponseStatus(code = HttpStatus.CREATED)
    public ChatsOutDto addChat(@RequestBody ChatsInDto chatsInDto) {
        Chats chats = chatsMapper.toEntity(chatsInDto);
        chats = chatsService.saveChats(chats);

        return chatsMapper.toDTO(chats);
    }

    @PutMapping("/chats/{id}")
    @ResponseStatus(code = HttpStatus.CREATED)
    public ChatsOutDto updateChat(@PathVariable int id, @RequestBody ChatsInDto chatsInDto) {
        Chats chats = chatsMapper.toEntity(chatsInDto);
        chats = chatsService.updateChat(id, chats);

        return chatsMapper.toDTO(chats);
    }

    @DeleteMapping("/chats/{id}")
    public String deleteChat(@PathVariable int id) {
        chatsService.deleteChat(id);
        return "\"information\": Чат с id = " + id + " больше не существует в базе данных.";
    }

}