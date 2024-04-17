package com.vistar.streamcinema.service;

import com.vistar.streamcinema.entity.Actors;
import com.vistar.streamcinema.entity.FilmActors;
import com.vistar.streamcinema.entity.Films;
import com.vistar.streamcinema.exception_handler.NotFoundException;
import com.vistar.streamcinema.repositories.ActorsRepository;
import com.vistar.streamcinema.repositories.FilmActorRepository;
import com.vistar.streamcinema.repositories.FilmsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class FilmActorsService {
    private final FilmActorRepository filmActorRepository;

    public List<FilmActors> getAllFilmActors() {
        return filmActorRepository.findAll();
    }

    @Transactional
    public FilmActors saveFilmActors(FilmActors filmActors) {
        filmActorRepository.save(filmActors);

        return filmActors;
    }

    @Transactional
    public void saveAllFilmActors(List<FilmActors> filmActors) {
        filmActorRepository.saveAll(filmActors);
    }

    public FilmActors getFilmActor(long id) {
        FilmActors filmActors = null;
        Optional<FilmActors> optional = filmActorRepository.findById(id);
        if (optional.isPresent()) {
            filmActors = optional.get();
        }
        return filmActors;
    }

    @Transactional
    public FilmActors updateFilmActor(long id, FilmActors filmActors) {
        FilmActors filmActor = filmActorRepository.findById(id).orElseThrow(() -> new NotFoundException("Невозможно обновить данные. Актёр фильма с id = " + id + " не найден в базе данных."));
        filmActor.setFilmID(filmActors.getFilmID());
        filmActor.setActorID(filmActors.getActorID());
        return filmActorRepository.save(filmActor);
    }

    @Transactional
    public void deleteFilmActor(long id) {
        filmActorRepository.deleteById(id);
    }

    private final FilmsRepository filmsRepository;

    public Films toFilms(long filmID) {
        return filmsRepository.findById(filmID).orElseThrow();
    }

    private final ActorsRepository actorsRepository;

    public Actors toActors(long actorID) {
        return actorsRepository.findById(actorID).orElseThrow();
    }
}
