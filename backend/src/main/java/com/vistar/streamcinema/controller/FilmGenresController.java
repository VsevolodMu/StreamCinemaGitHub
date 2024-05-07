package com.vistar.streamcinema.controller;

import com.vistar.streamcinema.dto.in.FilmGenresInDto;
import com.vistar.streamcinema.dto.out.FilmGenresOutDto;
import com.vistar.streamcinema.entity.FilmGenres;
import com.vistar.streamcinema.exception_handler.NotFoundException;
import com.vistar.streamcinema.mapper.FilmGenresMapper;
import com.vistar.streamcinema.service.FilmGenresService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class FilmGenresController {
    private final FilmGenresService filmGenresService;
    private final FilmGenresMapper filmGenresMapper;

    @GetMapping("/film-genres")
    public List<FilmGenresOutDto> showAllFilmsGenres() {
        List<FilmGenres> allFilmGenres = filmGenresService.getAllFilmGenres();

        return allFilmGenres.stream().map(filmGenresMapper::toDTO).collect(Collectors.toList());
    }

    @GetMapping("/film-genres/{id}")
    public FilmGenresOutDto showFilmGenre(@PathVariable int id) {
        FilmGenres filmGenres = filmGenresService.getFilmGenre(id);
        if (filmGenres == null) {
            throw new NotFoundException("Неверно уканазанный идентификатор. Жанр фильма с id = " + id + " не найден в базе данных.");
        }

        return filmGenresMapper.toDTO(filmGenres);
    }

    @PostMapping("/film-genres")
    @ResponseStatus(code = HttpStatus.CREATED)
    public FilmGenresOutDto addFilmGenre(@RequestBody FilmGenresInDto filmGenresInDto) {
        FilmGenres filmGenres = filmGenresMapper.toEntity(filmGenresInDto);
        filmGenres = filmGenresService.saveFilmGenres(filmGenres);

        return filmGenresMapper.toDTO(filmGenres);
    }

    @PutMapping("/film-genres/{id}")
    @ResponseStatus(code = HttpStatus.CREATED)
    public FilmGenresOutDto updateFilmGenre(@PathVariable int id, @RequestBody FilmGenresInDto filmGenresInDto) {
        FilmGenres filmGenres = filmGenresMapper.toEntity(filmGenresInDto);
        filmGenres = filmGenresService.updateFilmGenre(id, filmGenres);

        return filmGenresMapper.toDTO(filmGenres);
    }

    @DeleteMapping("/film-genres/{id}")
    public String deleteFilmGenre(@PathVariable int id) {
        filmGenresService.deleteFilmGenre(id);
        return "\"information\": Жанр фильма с id = " + id + " больше не существует в базе данных.";
    }

}
