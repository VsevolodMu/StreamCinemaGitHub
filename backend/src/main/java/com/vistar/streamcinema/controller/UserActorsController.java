package com.vistar.streamcinema.controller;

import com.vistar.streamcinema.dto.in.UserActorsInDto;
import com.vistar.streamcinema.dto.out.UserActorsOutDto;
import com.vistar.streamcinema.entity.UserActors;
import com.vistar.streamcinema.exception_handler.NotFoundException;
import com.vistar.streamcinema.mapper.UserActorsMapper;
import com.vistar.streamcinema.service.UserActorsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class UserActorsController {
    private final UserActorsService userActorsService;
    private final UserActorsMapper userActorsMapper;

    @GetMapping("/user-actors")
    public List<UserActorsOutDto> showAllFilmsGenres() {
        List<UserActors> allUserActors = userActorsService.getAllUserActors();

        return allUserActors.stream().map(userActorsMapper::toDTO).collect(Collectors.toList());
    }

    @GetMapping("/user-actors/{id}")
    public UserActorsOutDto showUserActor(@PathVariable int id) {
        UserActors userActors = userActorsService.getUserActor(id);
        if (userActors == null) {
            throw new NotFoundException("Неверно уканазанный идентификатор. Актёр пользователя с id = " + id + " не найден в базе данных.");
        }

        return userActorsMapper.toDTO(userActors);
    }

    @PostMapping("/user-actors")
    @ResponseStatus(code = HttpStatus.CREATED)
    public UserActorsOutDto addUserActor(@RequestBody UserActorsInDto userActorsInDto) {
        UserActors userActors = userActorsMapper.toEntity(userActorsInDto);
        userActors = userActorsService.saveUserActors(userActors);

        return userActorsMapper.toDTO(userActors);
    }

    @PutMapping("/user-actors/{id}")
    @ResponseStatus(code = HttpStatus.CREATED)
    public UserActorsOutDto updateUserActor(@PathVariable int id, @RequestBody UserActorsInDto userActorsInDto) {
        UserActors userActors = userActorsMapper.toEntity(userActorsInDto);
        userActors = userActorsService.updateUserActor(id, userActors);

        return userActorsMapper.toDTO(userActors);
    }

    @DeleteMapping("/user-actors/{id}")
    public String deleteUserActor(@PathVariable int id) {
        userActorsService.deleteUserActor(id);
        return "\"information\": Актёр пользователя с id = " + id + " больше не существует в базе данных.";
    }

}
