package com.vistar.streamcinema.controller;

import com.vistar.streamcinema.dto.in.UsersInDto;
import com.vistar.streamcinema.dto.out.UsersOutDto;
import com.vistar.streamcinema.entity.Users;
import com.vistar.streamcinema.exception_handler.IncorrectData;
import com.vistar.streamcinema.exception_handler.NotFoundException;
import com.vistar.streamcinema.mapper.UsersMapper;
import com.vistar.streamcinema.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class UsersController {
    private final UsersService usersService;
    private final UsersMapper usersMapper;

    @GetMapping("/users")
    public List<UsersOutDto> showAllUsers() {
        List<Users> allUsers = usersService.getAllUsers();

        return allUsers.stream().map(usersMapper::toDTO).collect(Collectors.toList());
    }

    @GetMapping("/users/{id}")
    public UsersOutDto showUser(@PathVariable long id) {
        Users users = usersService.getUser(id);
        if (users == null) {
            throw new NotFoundException("Неверно уканазанный идентификатор. Пользователь с id = " + id + " не найден в базе данных.");
        }
        return usersMapper.toDTO(users);
    }

    @PostMapping("/users")
    @ResponseStatus(code = HttpStatus.CREATED)
    public UsersOutDto addUser(@RequestBody UsersInDto usersInDto) {
        Users users = usersMapper.toEntity(usersInDto);
        users = usersService.saveUsers(users);

        return usersMapper.toDTO(users);
    }

    @PutMapping("/users/{id}")
    @ResponseStatus(code = HttpStatus.CREATED)
    public UsersOutDto updateUser(@PathVariable long id, @RequestBody UsersInDto usersInDto) {
        Users users = usersMapper.toEntity(usersInDto);
        users = usersService.updateUser(id, users);

        return usersMapper.toDTO(users);
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable long id) {
        usersService.deleteUser(id);
        return "\"information\": Пользователь с id = " + id + " больше не существует в базе данных.";
    }

    @ExceptionHandler
    public ResponseEntity<IncorrectData> handleException(NotFoundException exception) {
        IncorrectData data = new IncorrectData();
        data.setInformation(exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<IncorrectData> handleException(Exception exception) {
        IncorrectData data = new IncorrectData();
        data.setInformation(exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }

}