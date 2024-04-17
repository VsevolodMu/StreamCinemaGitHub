package com.vistar.streamcinema.service;

import com.vistar.streamcinema.entity.Genres;
import com.vistar.streamcinema.exception_handler.NotFoundException;
import com.vistar.streamcinema.repositories.GenresRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class GenresService {

    private final GenresRepository genresRepository;

    public List<Genres> getAllGenres() {
        return genresRepository.findAll();
    }

    @Transactional
    public Genres saveGenres(Genres genre) {
        genresRepository.save(genre);

        return genre;
    }

    @Transactional
    public List<Genres> saveAllGenres(List<Genres> genres) {
        genresRepository.saveAll(genres);

        return genres;
    }

    public Genres getGenre(long id) {
        Genres genre = null;
        Optional<Genres> optional = genresRepository.findById(id);
        if (optional.isPresent()) {
            genre = optional.get();
        }
        return genre;
    }

    @Transactional
    public void deleteGenre(long id) {
        genresRepository.deleteById(id);
    }

    @Transactional
    public Genres updateGenres(long id, Genres genres) {
        Genres genre = genresRepository.findById(id).orElseThrow(() -> new NotFoundException("Невозможно обновить данные. Жанр с id = " + id + " не найден в базе данных."));
        genre.setTitle(genres.getTitle());
        return genresRepository.save(genre);
    }

    public List<Genres> findAll() {
        return genresRepository.findAll();
    }

}
