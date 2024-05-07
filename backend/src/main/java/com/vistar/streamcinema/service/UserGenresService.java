package com.vistar.streamcinema.service;

import com.vistar.streamcinema.entity.Genres;
import com.vistar.streamcinema.entity.UserGenres;
import com.vistar.streamcinema.entity.Users;
import com.vistar.streamcinema.exception_handler.NotFoundException;
import com.vistar.streamcinema.repositories.GenresRepository;
import com.vistar.streamcinema.repositories.UserGenreRepository;
import com.vistar.streamcinema.repositories.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserGenresService {
    private final UserGenreRepository userGenreRepository;

    public List<UserGenres> getAllUserGenres() {
        return userGenreRepository.findAll();
    }

    @Transactional
    public UserGenres saveUserGenres(UserGenres userGenres) {
        userGenreRepository.save(userGenres);

        return userGenres;
    }

    @Transactional
    public List<UserGenres> saveAllUserGenres(List<UserGenres> userGenres) {
        userGenreRepository.saveAll(userGenres);

        return userGenres;
    }

    public UserGenres getUserGenre(long id) {
        UserGenres userGenres = null;
        Optional<UserGenres> optional = userGenreRepository.findById(id);
        if (optional.isPresent()) {
            userGenres = optional.get();
        }
        return userGenres;
    }

    @Transactional
    public UserGenres updateUserGenre(long id, UserGenres userGenres) {
        UserGenres userGenre = userGenreRepository.findById(id).orElseThrow(() -> new NotFoundException("Невозможно обновить данные. Жанр пользователя с id = " + id + " не найден в базе данных."));
        userGenre.setUserID(userGenres.getUserID());
        userGenre.setGenreID(userGenres.getGenreID());
        return userGenreRepository.save(userGenre);
    }

    @Transactional
    public void deleteUserGenre(long id) {
        userGenreRepository.deleteById(id);
    }

    private final GenresRepository genresRepository;

    public Genres toGenres(long genreID) {
        return genresRepository.findById(genreID).orElseThrow();
    }

    private final UsersRepository usersRepository;

    public Users toUsers(long userID) {
        return usersRepository.findById(userID).orElseThrow();
    }

}