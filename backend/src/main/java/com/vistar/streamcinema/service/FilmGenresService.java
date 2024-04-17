package com.vistar.streamcinema.service;

import com.vistar.streamcinema.entity.FilmGenres;
import com.vistar.streamcinema.entity.Films;
import com.vistar.streamcinema.entity.Genres;
import com.vistar.streamcinema.exception_handler.NotFoundException;
import com.vistar.streamcinema.repositories.FilmGenreRepository;
import com.vistar.streamcinema.repositories.FilmsRepository;
import com.vistar.streamcinema.repositories.GenresRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class FilmGenresService {
    private final FilmGenreRepository filmGenreRepository;

    public List<FilmGenres> getAllFilmGenres() {
        return filmGenreRepository.findAll();
    }

    @Transactional
    public FilmGenres saveFilmGenres(FilmGenres filmGenres) {
        filmGenreRepository.save(filmGenres);

        return filmGenres;
    }

    @Transactional
    public void saveAllFilmGenres(List<FilmGenres> filmGenres) {
        filmGenreRepository.saveAll(filmGenres);
    }

    public FilmGenres getFilmGenre(long id) {
        FilmGenres users = null;
        Optional<FilmGenres> optional = filmGenreRepository.findById(id);
        if (optional.isPresent()) {
            users = optional.get();
        }
        return users;
    }

    @Transactional
    public FilmGenres updateFilmGenre(long id, FilmGenres filmGenres) {
        FilmGenres filmGenre = filmGenreRepository.findById(id).orElseThrow(() -> new NotFoundException("Невозможно обновить данные. Жанр фильма с id = " + id + " не найден в базе данных."));
        filmGenre.setFilmID(filmGenres.getFilmID());
        filmGenre.setGenreID(filmGenres.getGenreID());
        return filmGenreRepository.save(filmGenre);
    }

    @Transactional
    public void deleteFilmGenre(long id) {
        filmGenreRepository.deleteById(id);
    }

    private final FilmsRepository filmsRepository;

    public Films toFilms(long filmID) {
        return filmsRepository.findById(filmID).orElseThrow();
    }

    private final GenresRepository genresRepository;

    public Genres toGenres(long genreID) {
        return genresRepository.findById(genreID).orElseThrow();
    }

}
