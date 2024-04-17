package com.vistar.streamcinema.service;

import com.vistar.streamcinema.entity.Actors;
import com.vistar.streamcinema.exception_handler.NotFoundException;
import com.vistar.streamcinema.repositories.ActorsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ActorsService {
    private final ActorsRepository actorsService;

    public List<Actors> getAllActors() {
        return actorsService.findAll();
    }

    @Transactional
    public Actors saveActors(Actors actors) {
        return actorsService.save(actors);
    }

    public Actors getActor(long id) {
        Actors actors = null;
        Optional<Actors> optional = actorsService.findById(id);
        if (optional.isPresent()) {
            actors = optional.get();
        }
        return actors;
    }

    @Transactional
    public void deleteActor(long id) {
        actorsService.deleteById(id);
    }

    @Transactional
    public Actors updateActor(long id, Actors actors) {
        Actors actor = actorsService.findById(id).orElseThrow(() -> new NotFoundException("Невозможно обновить данные. Актёр с id = " + id + " не найден в базе данных."));
        actor.setBiography(actors.getBiography());
        actor.setBirth(actors.getBirth());
        actor.setName(actors.getName());
        actor.setSurname(actors.getSurname());
        return actorsService.save(actor);
    }

}
