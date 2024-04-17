package com.vistar.streamcinema.service;

import com.vistar.streamcinema.entity.Chats;
import com.vistar.streamcinema.entity.Messages;
import com.vistar.streamcinema.entity.Users;
import com.vistar.streamcinema.exception_handler.NotFoundException;
import com.vistar.streamcinema.repositories.ChatsRepository;
import com.vistar.streamcinema.repositories.MessagesRepository;
import com.vistar.streamcinema.repositories.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MessagesService {
    private final MessagesRepository messagesRepository;

    public List<Messages> getAllMessages() {
        return messagesRepository.findAll();
    }

    @Transactional
    public Messages saveMessages(Messages messages) {
        return messagesRepository.save(messages);
    }

    public Messages getMessage(long id) {
        Messages messages = null;
        Optional<Messages> optional = messagesRepository.findById(id);
        if (optional.isPresent()) {
            messages = optional.get();
        }
        return messages;
    }

    @Transactional
    public Messages updateMessage(long id, Messages messages) {
        Messages message = messagesRepository.findById(id).orElseThrow(() -> new NotFoundException("Невозможно обновить данные. Сообщение с id = " + id + " не найдено в базе данных."));
        message.setChatID(messages.getChatID());
        message.setSenderID(messages.getSenderID());
        message.setMessageText(messages.getMessageText());
        return messagesRepository.save(message);
    }

    @Transactional
    public void deleteMessage(long id) {
        messagesRepository.deleteById(id);
    }

    private final ChatsRepository chatsRepository;

    public Chats toChats(long chatID) {
        return chatsRepository.findById(chatID).orElseThrow();
    }

    private final UsersRepository usersRepository;

    public Users toUsers(long userID) {
        return usersRepository.findById(userID).orElseThrow();
    }
}
