package com.suaimori.backend.controllers;

import com.suaimori.backend.model.dto.FriendRequest;
import com.suaimori.backend.model.entities.User;
import com.suaimori.backend.services.FriendService;
import com.suaimori.backend.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/friend")
@RequiredArgsConstructor
public class FriendController {

    private final FriendService friendService;
    private final UserService userService;

    @PostMapping("/create")
        public ResponseEntity<?> createFriend(@Valid @RequestBody FriendRequest friendRequest) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User creator = userService.findByUsername(userDetails.getUsername());
        User friend = userService.findByUsername(friendRequest.getReceiverUsername());
        friendService.create(creator, friend);
        return ResponseEntity.ok().build();
    }

}
