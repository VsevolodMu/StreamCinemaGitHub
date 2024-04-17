package com.vistar.streamcinema.service;

import com.vistar.streamcinema.entity.RoomUsers;
import com.vistar.streamcinema.entity.Rooms;
import com.vistar.streamcinema.entity.Users;
import com.vistar.streamcinema.exception_handler.NotFoundException;
import com.vistar.streamcinema.repositories.RoomUsersRepository;
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
public class RoomUsersService {
    private final RoomUsersRepository roomUsersRepository;

    public List<RoomUsers> getAllRoomUsers() {
        return roomUsersRepository.findAll();
    }

    @Transactional
    public RoomUsers saveRoomUsers(RoomUsers roomUsers) {
        roomUsersRepository.save(roomUsers);
        return roomUsers;
    }

    public RoomUsers getRoomUser(long id) {
        RoomUsers roomUsers = null;
        Optional<RoomUsers> optional = roomUsersRepository.findById(id);
        if (optional.isPresent()) {
            roomUsers = optional.get();
        }
        return roomUsers;
    }

    @Transactional
    public RoomUsers updateRoomUser(long id, RoomUsers roomUsers) {
        RoomUsers roomUser = roomUsersRepository.findById(id).orElseThrow(() -> new NotFoundException("Невозможно обновить данные. Комната пользователя с id = " + id + " не найдена в базе данных."));
        roomUser.setRoomID(roomUsers.getRoomID());
        roomUser.setUserID(roomUsers.getUserID());
        return roomUsersRepository.save(roomUser);
    }

    @Transactional
    public void deleteRoomUser(long id) {
        roomUsersRepository.deleteById(id);
    }

    private final UsersRepository usersRepository;

    public Users toUsers(long userID) {
        return usersRepository.findById(userID).orElseThrow();
    }

    private final RoomsRepository roomsRepository;

    public Rooms toRooms(long roomID) {
        return roomsRepository.findById(roomID).orElseThrow();
    }

}
