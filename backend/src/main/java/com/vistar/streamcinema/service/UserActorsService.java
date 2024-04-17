package com.vistar.streamcinema.service;

import com.vistar.streamcinema.entity.Actors;
import com.vistar.streamcinema.entity.UserActors;
import com.vistar.streamcinema.entity.Users;
import com.vistar.streamcinema.exception_handler.NotFoundException;
import com.vistar.streamcinema.repositories.ActorsRepository;
import com.vistar.streamcinema.repositories.UserActorRepository;
import com.vistar.streamcinema.repositories.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserActorsService {
    private final UserActorRepository userActorRepository;

    public List<UserActors> getAllUserActors() {
        return userActorRepository.findAll();
    }

    @Transactional
    public UserActors saveUserActors(UserActors userActors) {
        userActorRepository.save(userActors);

        return userActors;
    }

    @Transactional
    public void saveAllUserActors(List<UserActors> userActors) {
        userActorRepository.saveAll(userActors);
    }

    public UserActors getUserActor(long id) {
        UserActors userActors = null;
        Optional<UserActors> optional = userActorRepository.findById(id);
        if (optional.isPresent()) {
            userActors = optional.get();
        }
        return userActors;
    }

    @Transactional
    public UserActors updateUserActor(long id, UserActors userActors) {
        UserActors userActor = userActorRepository.findById(id).orElseThrow(() -> new NotFoundException("Невозможно обновить данные. Актёр пользователя с id = " + id + " не найден в базе данных."));
        userActor.setUserID(userActors.getUserID());
        userActor.setActorID(userActors.getActorID());
        return userActorRepository.save(userActor);
    }

    @Transactional
    public void deleteUserActor(long id) {
        userActorRepository.deleteById(id);
    }

    private final UsersRepository usersRepository;

    public Users toUsers(long userID) {
        return usersRepository.findById(userID).orElseThrow();
    }

    private final ActorsRepository actorsRepository;

    public Actors toActors(long actorID) {
        return actorsRepository.findById(actorID).orElseThrow();
    }
}
