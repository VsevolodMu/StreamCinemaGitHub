package com.vistar.streamcinema.controller;

import com.vistar.streamcinema.dto.in.RoomUsersInDto;
import com.vistar.streamcinema.dto.out.RoomUsersOutDto;
import com.vistar.streamcinema.entity.RoomUsers;
import com.vistar.streamcinema.exception_handler.NotFoundException;
import com.vistar.streamcinema.mapper.RoomUsersMapper;
import com.vistar.streamcinema.service.RoomUsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class RoomUsersController {
    private final RoomUsersService roomUsersService;
    private final RoomUsersMapper roomUsersMapper;

    @GetMapping("/rooms-users")
    public List<RoomUsersOutDto> showAllRoomUsers() {
        List<RoomUsers> allRoomUsers = roomUsersService.getAllRoomUsers();

        return allRoomUsers.stream().map(roomUsersMapper::toDTO).collect(Collectors.toList());
    }

    @GetMapping("/rooms-users/{id}")
    public RoomUsersOutDto showRoomUser(@PathVariable int id) {
        RoomUsers roomUsers = roomUsersService.getRoomUser(id);
        if (roomUsers == null) {
            throw new NotFoundException("Неверно уканазанный идентификатор. Комната пользователя с id = " + id + " не найдена в базе данных.");
        }

        return roomUsersMapper.toDTO(roomUsers);
    }

    @PostMapping("/room-users")
    @ResponseStatus(code = HttpStatus.CREATED)
    public RoomUsersOutDto addRoomUser(@RequestBody RoomUsersInDto roomUsersInDto) {
        RoomUsers roomUsers = roomUsersMapper.toEntity(roomUsersInDto);
        roomUsers = roomUsersService.saveRoomUsers(roomUsers);

        return roomUsersMapper.toDTO(roomUsers);
    }

    @PutMapping("/room-users/{id}")
    @ResponseStatus(code = HttpStatus.CREATED)
    public RoomUsersOutDto updateRoomUser(@PathVariable int id, @RequestBody RoomUsersInDto roomUsersInDto) {
        RoomUsers roomUsers = roomUsersMapper.toEntity(roomUsersInDto);
        roomUsers = roomUsersService.updateRoomUser(id, roomUsers);

        return roomUsersMapper.toDTO(roomUsers);
    }

    @DeleteMapping("/room-users/{id}")
    public String deleteRoomUser(@PathVariable int id) {
        roomUsersService.deleteRoomUser(id);
        return "\"information\": Комната пользователя с id = " + id + " больше не существует в базе данных.";
    }

}
