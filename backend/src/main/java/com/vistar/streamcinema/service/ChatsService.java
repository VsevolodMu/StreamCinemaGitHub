package com.vistar.streamcinema.service;

import com.vistar.streamcinema.entity.Chats;
import com.vistar.streamcinema.exception_handler.NotFoundException;
import com.vistar.streamcinema.repositories.ChatsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ChatsService {
    private final ChatsRepository chatsRepository;

    public List<Chats> getAllChats() {
        return chatsRepository.findAll();
    }

    @Transactional
    public Chats saveChats(Chats chats) {
        chatsRepository.save(chats);
        return chats;
    }

    public Chats getChat(long id) {
        Chats chats = null;
        Optional<Chats> optional = chatsRepository.findById(id);
        if (optional.isPresent()) {
            chats = optional.get();
        }
        return chats;
    }

    @Transactional
    public void deleteChat(long id) {
        chatsRepository.deleteById(id);
    }

    @Transactional
    public Chats updateChat(long id, Chats chats) {
        Chats chat = chatsRepository.findById(id).orElseThrow(() -> new NotFoundException("Невозможно обновить данные. Чат с id = " + id + " не найден в базе данных."));
        chat.setLastMessageTime(chats.getLastMessageTime());
        chat.setUserStatus(chats.getUserStatus());
        chat.setName(chats.getName());
        chat.setLastMessage(chats.getLastMessage());
        return chatsRepository.save(chat);
    }
}
