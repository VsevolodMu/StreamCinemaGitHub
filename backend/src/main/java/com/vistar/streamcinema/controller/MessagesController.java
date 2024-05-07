package com.vistar.streamcinema.controller;

import com.vistar.streamcinema.dto.in.MessagesInDto;
import com.vistar.streamcinema.dto.out.MessagesOutDto;
import com.vistar.streamcinema.entity.Messages;
import com.vistar.streamcinema.exception_handler.NotFoundException;
import com.vistar.streamcinema.mapper.MessagesMapper;
import com.vistar.streamcinema.service.MessagesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class MessagesController {
    private final MessagesService messagesService;
    private final MessagesMapper messagesMapper;

    @GetMapping("/messages")
    public List<MessagesOutDto> showAlRooms() {
        List<Messages> allMessages = messagesService.getAllMessages();

        return allMessages.stream().map(messagesMapper::toDTO).collect(Collectors.toList());
    }

    @GetMapping("/messages/{id}")
    public MessagesOutDto showMessage(@PathVariable int id) {
        Messages messages = messagesService.getMessage(id);
        if (messages == null) {
            throw new NotFoundException("Неверно уканазанный идентификатор. Сообщение с id = " + id + " не найдено в базе данных.");
        }

        return messagesMapper.toDTO(messages);
    }

    @PostMapping("/messages")
    @ResponseStatus(code = HttpStatus.CREATED)
    public MessagesOutDto addMessage(@RequestBody MessagesInDto messagesInDto) {
        Messages messages = messagesMapper.toEntity(messagesInDto);
        messages = messagesService.saveMessages(messages);

        return messagesMapper.toDTO(messages);
    }

    @PutMapping("/messages/{id}")
    @ResponseStatus(code = HttpStatus.CREATED)
    public MessagesOutDto updateMessage(@PathVariable int id, @RequestBody MessagesInDto messagesInDto) {
        Messages messages = messagesMapper.toEntity(messagesInDto);
        messages = messagesService.updateMessage(id, messages);

        return messagesMapper.toDTO(messages);
    }

    @DeleteMapping("/messages/{id}")
    public String deleteMessage(@PathVariable int id) {
        messagesService.deleteMessage(id);
        return "\"information\": Сообщение с id = " + id + " больше не существует в базе данных.";
    }

}
