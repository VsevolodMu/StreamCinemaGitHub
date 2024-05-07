package com.vistar.streamcinema.controller;

import com.vistar.streamcinema.dto.in.FilmsInDto;
import com.vistar.streamcinema.dto.out.FilmsOutDto;
import com.vistar.streamcinema.entity.Films;
import com.vistar.streamcinema.exception_handler.NotFoundException;
import com.vistar.streamcinema.mapper.FilmsMapper;
import com.vistar.streamcinema.service.FilmsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class FilmsController {
    private final FilmsService filmsService;
    private final FilmsMapper filmsMapper;

    @GetMapping("/films")
    public List<FilmsOutDto> showAllFilms() {
        List<Films> allFilms = filmsService.getAllFilms();

        return allFilms.stream().map(filmsMapper::toDTO).collect(Collectors.toList());
    }

    @GetMapping("/films/{id}")
    public FilmsOutDto showFilm(@PathVariable long id) {
        Films films = filmsService.getFilm(id);
        if (films == null) {
            throw new NotFoundException("Неверно указанный идентификатор. Фильм с id = " + id + " не найден в базе данных.");
        }
        return filmsMapper.toDTO(films);
    }

    @GetMapping("/films/themostpopular")
    public List<FilmsOutDto> showTheMostPopularFilms() {
        List<Films> allFilms = filmsService.getTheMostPopularFilms();

        return allFilms.stream().map(filmsMapper::toDTO).collect(Collectors.toList());
    }

    @PostMapping("/films")
    @ResponseStatus(code = HttpStatus.CREATED)
    public FilmsOutDto addFilm(@RequestBody FilmsInDto filmsInDto) {
        Films films = filmsMapper.toEntity(filmsInDto);
        films = filmsService.saveFilms(films);

        return filmsMapper.toDTO(films);
    }

    @PutMapping("/films/{id}")
    @ResponseStatus(code = HttpStatus.CREATED)
    public FilmsOutDto updateFilm(@PathVariable long id, @RequestBody FilmsInDto filmsInDto) {
        Films films = filmsMapper.toEntity(filmsInDto);
        films = filmsService.updateFilm(id, films);

        return filmsMapper.toDTO(films);
    }

    @DeleteMapping("/films/{id}")
    public String deleteFilm(@PathVariable long id) {
        filmsService.deleteFilm(id);
        return "\"information\": Фильм с id = " + id + " больше не существует в базе данных.";
    }

}
