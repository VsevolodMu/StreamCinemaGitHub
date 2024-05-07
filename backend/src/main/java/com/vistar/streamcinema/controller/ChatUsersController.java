package com.vistar.streamcinema.controller;

import com.vistar.streamcinema.dto.in.ChatUsersInDto;
import com.vistar.streamcinema.dto.out.ChatUsersOutDto;
import com.vistar.streamcinema.entity.ChatUsers;
import com.vistar.streamcinema.exception_handler.NotFoundException;
import com.vistar.streamcinema.mapper.ChatUsersMapper;
import com.vistar.streamcinema.service.ChatUsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class ChatUsersController {
    private final ChatUsersService chatUsersService;
    private final ChatUsersMapper chatUsersMapper;

    @GetMapping("/chats-users")
    public List<ChatUsersOutDto> showAllChatUsers() {
        List<ChatUsers> allChatUsers = chatUsersService.getAllChatUsers();

        return allChatUsers.stream().map(chatUsersMapper::toDTO).collect(Collectors.toList());
    }

    @GetMapping("/chats-users/{id}")
    public ChatUsersOutDto showChatUser(@PathVariable int id) {
        ChatUsers chatUsers = chatUsersService.getChatUser(id);
        if (chatUsers == null) {
            throw new NotFoundException("Неверно уканазанный идентификатор. Чат пользователя с id = " + id + " не найден в базе данных.");
        }

        return chatUsersMapper.toDTO(chatUsers);
    }

    @PostMapping("/chats-users")
    @ResponseStatus(code = HttpStatus.CREATED)
    public ChatUsersOutDto addChatUser(@RequestBody ChatUsersInDto chatUsersInDto) {
        ChatUsers chatUsers = chatUsersMapper.toEntity(chatUsersInDto);
        chatUsers = chatUsersService.saveChatUsers(chatUsers);

        return chatUsersMapper.toDTO(chatUsers);
    }

    @PutMapping("/chats-users/{id}")
    @ResponseStatus(code = HttpStatus.CREATED)
    public ChatUsersOutDto updateChatUser(@PathVariable int id, @RequestBody ChatUsersInDto chatUsersInDto) {
        ChatUsers chatUsers = chatUsersMapper.toEntity(chatUsersInDto);
        chatUsers = chatUsersService.updateChatUser(id, chatUsers);

        return chatUsersMapper.toDTO(chatUsers);
    }

    @DeleteMapping("/chats-users/{id}")
    public String deleteChatUser(@PathVariable int id) {
        chatUsersService.deleteChatUser(id);
        return "\"information\": Чат пользователя с id = " + id + " больше не существует в базе данных.";
    }

}
