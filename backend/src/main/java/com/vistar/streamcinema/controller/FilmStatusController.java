package com.vistar.streamcinema.controller;

import com.vistar.streamcinema.dto.in.FilmStatusInDto;
import com.vistar.streamcinema.dto.out.FilmStatusOutDto;
import com.vistar.streamcinema.entity.FilmStatus;
import com.vistar.streamcinema.exception_handler.NotFoundException;
import com.vistar.streamcinema.mapper.FilmStatusMapper;
import com.vistar.streamcinema.service.FilmStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class FilmStatusController {
    private final FilmStatusService filmStatusService;
    private final FilmStatusMapper filmStatusMapper;

    @GetMapping("/film-status")
    public List<FilmStatusOutDto> showAllFilmsStatus() {
        List<FilmStatus> allFilmStatus = filmStatusService.getAllFilmStatus();


        return allFilmStatus.stream().map(filmStatusMapper::toDTO).collect(Collectors.toList());
    }

    @GetMapping("/film-status/{id}")
    public FilmStatusOutDto showFilmStatus(@PathVariable int id) {
        FilmStatus filmStatus = filmStatusService.getFilmStatus(id);
        if (filmStatus == null) {
            throw new NotFoundException("Неверно уканазанный идентификатор. Статус фильма с id = " + id + " не найден в базе данных.");
        }

        return filmStatusMapper.toDTO(filmStatus);
    }

    @PostMapping("/film-status")
    @ResponseStatus(code = HttpStatus.CREATED)
    public FilmStatusOutDto addFilmStatus(@RequestBody FilmStatusInDto filmStatusInDto) {
        FilmStatus filmStatus = filmStatusMapper.toEntity(filmStatusInDto);
        filmStatus = filmStatusService.saveFilmStatus(filmStatus);

        return filmStatusMapper.toDTO(filmStatus);
    }

    @PutMapping("/film-status/{id}")
    @ResponseStatus(code = HttpStatus.CREATED)
    public FilmStatusOutDto updateFilmStatus(@PathVariable int id, @RequestBody FilmStatusInDto filmStatusInDto) {
        FilmStatus filmStatus = filmStatusMapper.toEntity(filmStatusInDto);
        filmStatus = filmStatusService.updateFilmStatus(id, filmStatus);


        return filmStatusMapper.toDTO(filmStatus);
    }

    @DeleteMapping("/film-status/{id}")
    public String deleteFilmStatus(@PathVariable int id) {
        filmStatusService.deleteFilmStatus(id);
        return "\"information\": Статус фильма с id = " + id + " больше не существует в базе данных.";
    }

}
