package com.vistar.streamcinema.service;

import com.vistar.streamcinema.entity.Films;
import com.vistar.streamcinema.entity.Rooms;
import com.vistar.streamcinema.entity.Users;
import com.vistar.streamcinema.exception_handler.NotFoundException;
import com.vistar.streamcinema.repositories.FilmsRepository;
import com.vistar.streamcinema.repositories.RoomsRepository;
import com.vistar.streamcinema.repositories.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class RoomsService {
    private final RoomsRepository roomsRepository;

    public List<Rooms> getAllRooms() {
        return roomsRepository.findAll();
    }

    @Transactional
    public Rooms saveRooms(Rooms rooms) {
        roomsRepository.save(rooms);

        return rooms;
    }

    public Rooms getRoom(long id) {
        Rooms rooms = null;
        Optional<Rooms> optional = roomsRepository.findById(id);
        if (optional.isPresent()) {
            rooms = optional.get();
        }
        return rooms;
    }

    @Transactional
    public Rooms updateRoom(long id, Rooms rooms) {
        Rooms room = roomsRepository.findById(id).orElseThrow(() -> new NotFoundException("Невозможно обновить данные. Комната с id = " + id + " не найдена в базе данных."));
        room.setTime(rooms.getTime());
        room.setDescription(rooms.getDescription());
        room.setFilmID(rooms.getFilmID());
        room.setCreatorID(rooms.getCreatorID());
        return roomsRepository.save(room);
    }

    @Transactional
    public void deleteRoom(long id) {
        roomsRepository.deleteById(id);
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
