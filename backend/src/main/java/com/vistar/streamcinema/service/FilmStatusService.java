package com.vistar.streamcinema.service;

import com.vistar.streamcinema.entity.FilmStatus;
import com.vistar.streamcinema.entity.Films;
import com.vistar.streamcinema.entity.Users;
import com.vistar.streamcinema.exception_handler.NotFoundException;
import com.vistar.streamcinema.repositories.FilmStatusRepository;
import com.vistar.streamcinema.repositories.FilmsRepository;
import com.vistar.streamcinema.repositories.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class FilmStatusService {
    private final FilmStatusRepository filmStatusRepository;

    public List<FilmStatus> getAllFilmStatus() {
        return filmStatusRepository.findAll();
    }

    @Transactional
    public FilmStatus saveFilmStatus(FilmStatus filmStatus) {
        filmStatusRepository.save(filmStatus);

        return filmStatus;
    }

    public FilmStatus getFilmStatus(long id) {
        FilmStatus filmStatus = null;
        Optional<FilmStatus> optional = filmStatusRepository.findById(id);
        if (optional.isPresent()) {
            filmStatus = optional.get();
        }
        return filmStatus;
    }

    @Transactional
    public FilmStatus updateFilmStatus(long id, FilmStatus filmStatus) {
        FilmStatus filmStatus1 = filmStatusRepository.findById(id).orElseThrow(() -> new NotFoundException("Невозможно обновить данные. Статус фильма с id = " + id + " не найден в базе данных."));
        filmStatus1.setUserID(filmStatus.getUserID());
        filmStatus1.setFilmID(filmStatus.getFilmID());
        filmStatus1.setFavorite(filmStatus.isFavorite());
        filmStatus1.setWatched(filmStatus.isWatched());
        filmStatus1.setPlanned(filmStatus.isPlanned());
        return filmStatusRepository.save(filmStatus1);
    }

    @Transactional
    public void deleteFilmStatus(long id) {
        filmStatusRepository.deleteById(id);
    }

    private final UsersRepository usersRepository;

    public Users toUsers(long userID) {
        return usersRepository.findById(userID).orElseThrow();
    }

    private final FilmsRepository filmsRepository;

    public Films toFilms(long filmID) {
        return filmsRepository.findById(filmID).orElseThrow();
    }

}
