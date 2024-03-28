package com.suaimori.backend.controllers;

import com.suaimori.backend.model.dto.CommentDTO;
import com.suaimori.backend.model.entities.Comment;
import com.suaimori.backend.model.entities.Title;
import com.suaimori.backend.model.entities.User;
import com.suaimori.backend.services.CommentService;
import com.suaimori.backend.services.TitleService;
import com.suaimori.backend.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final UserService userService;
    private final TitleService titleService;

    @PostMapping("/create")
    public ResponseEntity<?> createComment(@Valid @RequestBody CommentDTO commentDTO) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User creator = userService.findByUsername(userDetails.getUsername());
        Title title = titleService.findByName(commentDTO.getTitle().getName());
        commentService.create(commentDTO, title, creator);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable Long id) {
        commentService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/hide/{id}")
    public ResponseEntity<?> hideComment(@PathVariable Long id) {
        commentService.hide(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/show/{id}")
    public ResponseEntity<?> showComment(@PathVariable Long id) {
        commentService.show(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody CommentDTO commentDTO){
        commentService.update(id, commentDTO);
        return ResponseEntity.ok().build();
    }
}
