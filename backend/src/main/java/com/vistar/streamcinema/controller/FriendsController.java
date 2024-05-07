package com.vistar.streamcinema.controller;

import com.vistar.streamcinema.dto.in.FriendsInDto;
import com.vistar.streamcinema.dto.out.FriendsOutDto;
import com.vistar.streamcinema.entity.Friends;
import com.vistar.streamcinema.exception_handler.NotFoundException;
import com.vistar.streamcinema.mapper.FriendsMapper;
import com.vistar.streamcinema.service.FriendsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class FriendsController {
    private final FriendsService friendsService;
    private final FriendsMapper friendsMapper;

    @GetMapping("/friends")
    public List<FriendsOutDto> showAllFriends() {
        List<Friends> allFriends = friendsService.getAllFriends();

        return allFriends.stream().map(friendsMapper::toDTO).collect(Collectors.toList());
    }

    @GetMapping("/friends/{id}")
    public FriendsOutDto showFriend(@PathVariable int id) {
        Friends friends = friendsService.getFriend(id);
        if (friends == null) {
            throw new NotFoundException("Неверно уканазанный идентификатор. Друг с id = " + id + " не найден в базе данных.");
        }

        return friendsMapper.toDTO(friends);
    }

    @PostMapping("/friends")
    @ResponseStatus(code = HttpStatus.CREATED)
    public FriendsOutDto addFriend(@RequestBody FriendsInDto friendsInDto) {
        Friends friends = friendsMapper.toEntity(friendsInDto);
        friends = friendsService.saveFriends(friends);

        return friendsMapper.toDTO(friends);
    }

    @PutMapping("/friends/{id}")
    @ResponseStatus(code = HttpStatus.CREATED)
    public FriendsOutDto updateFriend(@PathVariable int id, @RequestBody FriendsInDto friendsInDto) {
        Friends friends = friendsMapper.toEntity(friendsInDto);
        friends = friendsService.updateFriend(id, friends);

        return friendsMapper.toDTO(friends);
    }

    @DeleteMapping("/friends/{id}")
    public String deleteFriend(@PathVariable int id) {
        friendsService.deleteFriend(id);
        return "\"information\": Друг с id = " + id + " больше не существует в базе данных.";
    }

}
