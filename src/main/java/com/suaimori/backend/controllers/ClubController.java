package com.suaimori.backend.controllers;

import com.suaimori.backend.model.dto.ClubDTO;
import com.suaimori.backend.model.dto.ClubJoinRequest;
import com.suaimori.backend.model.entities.Club;
import com.suaimori.backend.model.entities.User;
import com.suaimori.backend.services.ClubService;
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
@RequestMapping("/club")
@RequiredArgsConstructor
public class ClubController {

    private final ClubService clubService;
    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<?> createClub(@Valid @RequestBody ClubDTO clubDTO) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User creator = userService.findByUsername(userDetails.getUsername()); // предполагается, что у вас есть метод findByUsername в UserService
        Club club = new Club(clubDTO);
        clubService.create(club, creator);
        return ResponseEntity.ok().build();
    }

    @PostMapping("join")
    public ResponseEntity<?> joinClub(@RequestBody ClubJoinRequest request) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.findByUsername(userDetails.getUsername());
        clubService.join(request.getName(), user);
        return ResponseEntity.ok().build();
    }
}
