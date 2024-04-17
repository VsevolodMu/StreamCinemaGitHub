package com.vistar.streamcinema.service;

import com.vistar.streamcinema.entity.Users;
import com.vistar.streamcinema.exception_handler.NotFoundException;
import com.vistar.streamcinema.repositories.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UsersService {
    private final UsersRepository usersRepository;

    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    @Transactional
    public Users saveUsers(Users users) {
        usersRepository.save(users);

        return users;
    }

    public Users getUser(long id) {
        Users users = null;
        Optional<Users> optional = usersRepository.findById(id);
        if (optional.isPresent()) {
            users = optional.get();
        }
        return users;
    }

    @Transactional
    public Users updateUser(long id, Users users) {
        Users user = usersRepository.findById(id).orElseThrow(() -> new NotFoundException("Невозможно обновить данные. Пользователь с id = " + id + " не найден в базе данных."));
        user.setUsername(users.getUsername());
        user.setPassword(users.getPassword());
        user.setEmail(users.getEmail());
        user.setFavoriteGenreID(users.getFavoriteGenreID());
        user.setProfileInfo(users.getProfileInfo());
        user.setNumber(users.getNumber());
        user.setBirth(users.getBirth());
        user.setLastLoginDate(users.getLastLoginDate());
        user.setRegistrationDate(users.getRegistrationDate());
        return usersRepository.save(user);
    }

    @Transactional
    public void deleteUser(long id) {
        usersRepository.deleteById(id);
    }

}
