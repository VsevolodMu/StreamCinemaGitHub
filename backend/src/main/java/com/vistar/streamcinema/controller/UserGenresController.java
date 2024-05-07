package com.vistar.streamcinema.controller;

import com.vistar.streamcinema.dto.in.UserGenresInDto;
import com.vistar.streamcinema.dto.out.UserGenresOutDto;
import com.vistar.streamcinema.entity.UserGenres;
import com.vistar.streamcinema.exception_handler.NotFoundException;
import com.vistar.streamcinema.mapper.UserGenresMapper;
import com.vistar.streamcinema.service.UserGenresService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class UserGenresController {
    private final UserGenresService userGenresService;
    private final UserGenresMapper userGenresMapper;

    @GetMapping("/user-genre")
    public List<UserGenresOutDto> showAllUserGenre() {
        List<UserGenres> allUserGenres = userGenresService.getAllUserGenres();

        return allUserGenres.stream().map(userGenresMapper::toDTO).collect(Collectors.toList());
    }

    @GetMapping("/user-genre/{id}")
    public UserGenresOutDto showUserGenre(@PathVariable int id) {
        UserGenres userGenres = userGenresService.getUserGenre(id);
        if (userGenres == null) {
            throw new NotFoundException("Неверно уканазанный идентификатор. Жанр пользователя с id = " + id + " не найден в базе данных.");
        }
        return userGenresMapper.toDTO(userGenres);
    }

    @PostMapping("/user-genre")
    @ResponseStatus(code = HttpStatus.CREATED)
    public UserGenresOutDto addUserGenre(@RequestBody UserGenresInDto userGenresInDto) {
        UserGenres userGenres = userGenresMapper.toEntity(userGenresInDto);
        userGenres = userGenresService.saveUserGenres(userGenres);

        return userGenresMapper.toDTO(userGenres);
    }

    @PutMapping("/user-genre/{id}")
    @ResponseStatus(code = HttpStatus.CREATED)
    public UserGenresOutDto updateUserGenre(@PathVariable int id, @RequestBody UserGenresInDto userGenresInDto) {
        UserGenres userGenres = userGenresMapper.toEntity(userGenresInDto);
        userGenres = userGenresService.updateUserGenre(id, userGenres);

        return userGenresMapper.toDTO(userGenres);
    }

    @DeleteMapping("/user-genre/{id}")
    public String deleteUserGenre(@PathVariable int id) {
        userGenresService.deleteUserGenre(id);
        return "\"information\": Жанра пользователя с id = " + id + " больше не существует в базе данных.";

    }

}
