package com.suaimori.backend.controllers;

import com.suaimori.backend.model.dto.RatingDTO;
import com.suaimori.backend.model.entities.Rating;
import com.suaimori.backend.model.entities.Title;
import com.suaimori.backend.model.entities.User;
import com.suaimori.backend.services.RatingService;
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
@RequestMapping("/rating")
@RequiredArgsConstructor
public class RatingController {

    private final RatingService ratingService;
    private final UserService userService;
    private final TitleService titleService;

    @PostMapping("/set")
    public ResponseEntity<?> setRating(@Valid @RequestBody RatingDTO ratingDTO) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User creator = userService.findByUsername(userDetails.getUsername());
        Title title = titleService.findByName(ratingDTO.getTitle().getName());
        Rating rating = new Rating(ratingDTO);
        ratingService.setRating(rating, title, creator);
        return ResponseEntity.ok().build();
    }
}
