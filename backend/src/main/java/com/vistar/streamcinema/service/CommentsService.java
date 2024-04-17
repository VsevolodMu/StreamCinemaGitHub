package com.vistar.streamcinema.service;

import com.vistar.streamcinema.entity.Comments;
import com.vistar.streamcinema.entity.Films;
import com.vistar.streamcinema.entity.Users;
import com.vistar.streamcinema.exception_handler.NotFoundException;
import com.vistar.streamcinema.repositories.CommentsRepository;
import com.vistar.streamcinema.repositories.FilmsRepository;
import com.vistar.streamcinema.repositories.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class CommentsService {
    private final CommentsRepository commentsRepository;

    public List<Comments> getAllComments() {
        return commentsRepository.findAll();
    }

    @Transactional
    public Comments saveComments(Comments comments) {
        return commentsRepository.save(comments);
    }

    public Comments getComment(long id) {
        Comments comments = null;
        Optional<Comments> optional = commentsRepository.findById(id);
        if (optional.isPresent()) {
            comments = optional.get();
        }
        return comments;
    }

    @Transactional
    public void deleteComment(long id) {
        commentsRepository.deleteById(id);
    }

    @Transactional
    public Comments updateComment(long id, Comments comments) {
        Comments comment = commentsRepository.findById(id).orElseThrow(() -> new NotFoundException("Невозможно обновить данные. Комментарий с id = " + id + " не найден в базе данных."));
        comment.setUserID(comments.getUserID());
        comment.setFilmID(comments.getFilmID());
        comment.setCommentText(comments.getCommentText());
        return commentsRepository.save(comment);
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
