package com.vistar.streamcinema.service;

import com.vistar.streamcinema.entity.ChatUsers;
import com.vistar.streamcinema.entity.Chats;
import com.vistar.streamcinema.entity.Users;
import com.vistar.streamcinema.exception_handler.NotFoundException;
import com.vistar.streamcinema.repositories.ChatUsersRepository;
import com.vistar.streamcinema.repositories.ChatsRepository;
import com.vistar.streamcinema.repositories.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ChatUsersService {
    private final ChatUsersRepository chatUsersRepository;

    public List<ChatUsers> getAllChatUsers() {
        return chatUsersRepository.findAll();
    }

    @Transactional
    public ChatUsers saveChatUsers(ChatUsers chatUsers) {
        chatUsersRepository.save(chatUsers);
        return chatUsers;
    }

    public ChatUsers getChatUser(long id) {
        ChatUsers chatUsers = null;
        Optional<ChatUsers> optional = chatUsersRepository.findById(id);
        if (optional.isPresent()) {
            chatUsers = optional.get();
        }
        return chatUsers;
    }

    @Transactional
    public void deleteChatUser(long id) {
        chatUsersRepository.deleteById(id);
    }

    @Transactional
    public ChatUsers updateChatUser(long id, ChatUsers chatUsers) {
        ChatUsers chatUser = chatUsersRepository.findById(id).orElseThrow(() -> new NotFoundException("Невозможно обновить данные. Чат пользователя с id = " + id + " не найден в базе данных."));
        chatUser.setChatID(chatUsers.getChatID());
        chatUser.setUserID(chatUsers.getUserID());
        return chatUsersRepository.save(chatUser);
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
