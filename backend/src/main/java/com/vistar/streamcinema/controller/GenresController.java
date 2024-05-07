package com.vistar.streamcinema.controller;

import com.vistar.streamcinema.dto.in.GenresInDto;
import com.vistar.streamcinema.dto.out.GenresOutDto;
import com.vistar.streamcinema.entity.Genres;
import com.vistar.streamcinema.exception_handler.NotFoundException;
import com.vistar.streamcinema.mapper.GenresMapper;
import com.vistar.streamcinema.service.GenresService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class GenresController {
    private final GenresService genresService;
    private final GenresMapper genresMapper;

    @GetMapping("/genres")
    public List<GenresOutDto> showAllGenres() {
        List<Genres> allGenres = genresService.getAllGenres();

        return allGenres.stream().map(genresMapper::toDTO).collect(Collectors.toList());
    }

    @GetMapping("/genres/{id}")
    public GenresOutDto showGenre(@PathVariable int id) {
        Genres genres = genresService.getGenre(id);
        if (genres == null) {
            throw new NotFoundException("Неверно уканазанный идентификатор. Жанр с id = " + id + " не найден в базе данных.");
        }

        return genresMapper.toDTO(genres);
    }

    @PostMapping("/genres")
    @ResponseStatus(code = HttpStatus.CREATED)
    public GenresOutDto addGenre(@RequestBody GenresInDto genresInDto) {
        Genres genres = genresMapper.toEntity(genresInDto);
        genres = genresService.saveGenres(genres);

        return genresMapper.toDTO(genres);
    }

    @PutMapping("/genres/{id}")
    @ResponseStatus(code = HttpStatus.CREATED)
    public GenresOutDto updateGenre(@PathVariable int id, @RequestBody GenresInDto genresInDto) {
        Genres genres = genresMapper.toEntity(genresInDto);
        genres = genresService.updateGenres(id, genres);

        return genresMapper.toDTO(genres);
    }

    @DeleteMapping("/genres/{id}")
    public String deleteGenre(@PathVariable int id) {
        genresService.deleteGenre(id);
        return "\"information\": Жанр с id = " + id + " больше не существует в базе данных.";
    }

}
