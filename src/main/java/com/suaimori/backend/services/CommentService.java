package com.suaimori.backend.services;

import com.suaimori.backend.model.entities.Comment;
import com.suaimori.backend.model.entities.Title;
import com.suaimori.backend.model.entities.User;
import com.suaimori.backend.repository.CommentRepository;
import com.suaimori.backend.repository.TitleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

        private final CommentRepository commentRepository;

        public void create(Comment comment, Title title, User creator){
            comment.setUser(creator);
            comment.setTitle(title);
            commentRepository.save(comment);
        }
}
