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
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/update/user/{userId}/title/{titleId}")
    public ResponseEntity<?> updateRating(@PathVariable Long userId, @PathVariable Long titleId, @Valid @RequestBody RatingDTO ratingDTO) {
        User creator = userService.findById(userId);
        Title title = titleService.findById(titleId);
        Rating rating = new Rating(ratingDTO);
        ratingService.update(rating, title, creator);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/user/{userId}/title/{titleId}")
    public ResponseEntity<?> deleteRating(@PathVariable Long userId, @PathVariable Long titleId) {
        ratingService.delete(userId, titleId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/hide/user/{userId}/title/{titleId}")
    public ResponseEntity<?> hideRating(@PathVariable Long userId, @PathVariable Long titleId) {
        ratingService.hide(userId, titleId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/show/user/{userId}/title/{titleId}")
    public ResponseEntity<?> showRating(@PathVariable Long userId, @PathVariable Long titleId) {
        ratingService.show(userId, titleId);
        return ResponseEntity.ok().build();
    }
}
