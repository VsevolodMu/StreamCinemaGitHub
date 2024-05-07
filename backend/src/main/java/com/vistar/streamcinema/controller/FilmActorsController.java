package com.vistar.streamcinema.controller;

import com.vistar.streamcinema.dto.in.FilmActorsInDto;
import com.vistar.streamcinema.dto.out.FilmActorsOutDto;
import com.vistar.streamcinema.entity.FilmActors;
import com.vistar.streamcinema.exception_handler.NotFoundException;
import com.vistar.streamcinema.mapper.FilmActorsMapper;
import com.vistar.streamcinema.service.FilmActorsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class FilmActorsController {
    private final FilmActorsService filmActorsService;
    private final FilmActorsMapper filmActorsMapper;

    @GetMapping("/film-actors")
    public List<FilmActorsOutDto> showAllFilmsGenres() {
        List<FilmActors> allFilmActors = filmActorsService.getAllFilmActors();

        return allFilmActors.stream().map(filmActorsMapper::toDTO).collect(Collectors.toList());
    }

    @GetMapping("/film-actors/{id}")
    public FilmActorsOutDto showFilmActor(@PathVariable int id) {
        FilmActors filmActors = filmActorsService.getFilmActor(id);
        if (filmActors == null) {
            throw new NotFoundException("Неверно уканазанный идентификатор. Актёр фильма с id = " + id + " не найден в базе данных.");
        }

        return filmActorsMapper.toDTO(filmActors);
    }

    @PostMapping("/film-actors")
    @ResponseStatus(code = HttpStatus.CREATED)
    public FilmActorsOutDto addFilmActor(@RequestBody FilmActorsInDto filmActorsInDto) {
        FilmActors filmActors = filmActorsMapper.toEntity(filmActorsInDto);
        filmActors = filmActorsService.saveFilmActors(filmActors);

        return filmActorsMapper.toDTO(filmActors);
    }

    @PutMapping("/film-actors/{id}")
    @ResponseStatus(code = HttpStatus.CREATED)
    public FilmActorsOutDto updateFilmActor(@PathVariable int id, @RequestBody FilmActorsInDto filmActorsInDto) {
        FilmActors filmActors = filmActorsMapper.toEntity(filmActorsInDto);
        filmActors = filmActorsService.updateFilmActor(id, filmActors);

        return filmActorsMapper.toDTO(filmActors);
    }

    @DeleteMapping("/film-actors/{id}")
    public String deleteFilmActor(@PathVariable int id) {
        filmActorsService.deleteFilmActor(id);
        return "\"information\": Актёр фильма с id = " + id + " больше не существует в базе данных.";
    }

}
