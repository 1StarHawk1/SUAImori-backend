package com.suaimori.backend.controllers;

import com.suaimori.backend.model.dto.CommentDTO;
import com.suaimori.backend.model.dto.ReviewDTO;
import com.suaimori.backend.model.entities.Comment;
import com.suaimori.backend.model.entities.Review;
import com.suaimori.backend.model.entities.Title;
import com.suaimori.backend.model.entities.User;
import com.suaimori.backend.services.ReviewService;
import com.suaimori.backend.services.TitleService;
import com.suaimori.backend.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
    private final UserService userService;
    private final TitleService titleService;

    @PostMapping("/create")
    public ResponseEntity<?> createComment(@Valid @RequestBody ReviewDTO reviewDTO) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User creator = userService.findByUsername(userDetails.getUsername());
        Title title = titleService.findByName(reviewDTO.getTitle().getName());
        Review review = new Review(reviewDTO);
        reviewService.create(review, title, creator);
        return ResponseEntity.ok().build();
    }
}
