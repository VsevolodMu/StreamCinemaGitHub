package com.vistar.streamcinema.service;

import com.vistar.streamcinema.entity.Films;
import com.vistar.streamcinema.exception_handler.NotFoundException;
import com.vistar.streamcinema.repositories.FilmsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class FilmsService {
    private final FilmsRepository filmsRepository;

    public List<Films> getAllFilms() {
        return filmsRepository.findAll();
    }

    @Transactional
    public Films saveFilms(Films films) {
        filmsRepository.save(films);

        return films;
    }

    public Films getFilm(long id) {
        Films films = null;
        Optional<Films> optional = filmsRepository.findById(id);
        if (optional.isPresent()) {
            films = optional.get();
        }
        return films;
    }

    @Transactional
    public List<Films> getTheMostPopularFilms() {
        return filmsRepository.findByRate(5);
    }

    @Transactional
    public Films updateFilm(long id, Films films) {
        Films film = filmsRepository.findById(id).orElseThrow(() -> new NotFoundException("Невозможно обновить данные. Фильм с id = " + id + " не найден в базе данных."));
        film.setTitle(films.getTitle());
        film.setDescription(films.getDescription());
        film.setRate(films.getRate());
        film.setCover(films.getCover());
        film.setPath(films.getPath());
        return filmsRepository.save(film);
    }

    @Transactional
    public void deleteFilm(long id) {
        filmsRepository.deleteById(id);
    }
}
