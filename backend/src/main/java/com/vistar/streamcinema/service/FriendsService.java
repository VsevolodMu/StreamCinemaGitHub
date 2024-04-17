package com.vistar.streamcinema.service;

import com.vistar.streamcinema.entity.Friends;
import com.vistar.streamcinema.entity.Users;
import com.vistar.streamcinema.exception_handler.NotFoundException;
import com.vistar.streamcinema.repositories.FriendsRepository;
import com.vistar.streamcinema.repositories.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class FriendsService {
    private final FriendsRepository friendsRepository;

    public List<Friends> getAllFriends() {
        return friendsRepository.findAll();
    }

    @Transactional
    public Friends saveFriends(Friends friends) {
        friendsRepository.save(friends);

        return friends;
    }

    public Friends getFriend(long id) {
        Friends friends = null;
        Optional<Friends> optional = friendsRepository.findById(id);
        if (optional.isPresent()) {
            friends = optional.get();
        }
        return friends;
    }

    @Transactional
    public Friends updateFriend(long id, Friends friends) {
        Friends friend = friendsRepository.findById(id).orElseThrow(() -> new NotFoundException("Невозможно обновить данные. Друг с id = " + id + " не найден в базе данных."));
        friend.setRecipientID(friends.getRecipientID());
        friend.setRequestID(friends.getRequestID());
        friend.setFriendshipStatus(friends.isFriendshipStatus());
        return friendsRepository.save(friend);
    }

    @Transactional
    public void deleteFriend(long id) {
        friendsRepository.deleteById(id);
    }

    private final UsersRepository usersRepository;

    public Users toUsers(long userID) {
        return usersRepository.findById(userID).orElseThrow();
    }
}
