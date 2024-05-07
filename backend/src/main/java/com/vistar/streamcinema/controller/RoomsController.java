package com.vistar.streamcinema.controller;

import com.vistar.streamcinema.dto.in.RoomsInDto;
import com.vistar.streamcinema.dto.out.RoomsOutDto;
import com.vistar.streamcinema.entity.Rooms;
import com.vistar.streamcinema.exception_handler.NotFoundException;
import com.vistar.streamcinema.mapper.RoomsMapper;
import com.vistar.streamcinema.service.RoomsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class RoomsController {
    private final RoomsService roomsService;
    private final RoomsMapper roomsMapper;

    @GetMapping("/rooms")
    public List<RoomsOutDto> showAlRooms() {
        List<Rooms> allRooms = roomsService.getAllRooms();

        return allRooms.stream().map(roomsMapper::toDTO).collect(Collectors.toList());
    }

    @GetMapping("/rooms/{id}")
    public RoomsOutDto showRoom(@PathVariable int id) {
        Rooms rooms = roomsService.getRoom(id);
        if (rooms == null) {
            throw new NotFoundException("Неверно уканазанный идентификатор. Комната с id = " + id + " не найдена в базе данных.");
        }

        return roomsMapper.toDTO(rooms);
    }

    @PostMapping("/rooms")
    @ResponseStatus(code = HttpStatus.CREATED)
    public RoomsOutDto addRoom(@RequestBody RoomsInDto roomsInDto) {
        Rooms rooms = roomsMapper.toEntity(roomsInDto);
        rooms = roomsService.saveRooms(rooms);

        return roomsMapper.toDTO(rooms);
    }

    @PutMapping("/rooms/{id}")
    @ResponseStatus(code = HttpStatus.CREATED)
    public RoomsOutDto updateRoom(@PathVariable int id, @RequestBody RoomsInDto roomsInDto) {
        Rooms rooms = roomsMapper.toEntity(roomsInDto);
        rooms = roomsService.updateRoom(id, rooms);

        return roomsMapper.toDTO(rooms);
    }

    @DeleteMapping("/rooms/{id}")
    public String deleteRoom(@PathVariable int id) {
        roomsService.deleteRoom(id);
        return "\"information\": Комната с id = " + id + " больше не существует в базе данных.";
    }

}
