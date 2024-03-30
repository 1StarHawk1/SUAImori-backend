package com.suaimori.backend.controllers;

import com.suaimori.backend.model.dto.AddTitleToListRequest;
import com.suaimori.backend.model.dto.UserListDTO;
import com.suaimori.backend.model.entities.User;
import com.suaimori.backend.model.entities.UserList;
import com.suaimori.backend.services.UserListService;
import com.suaimori.backend.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/list")
@RequiredArgsConstructor
public class UserListController {

    private final UserListService userListService;
    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<?> createList(@RequestBody UserListDTO userListDTO) throws ChangeSetPersister.NotFoundException {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User creator = userService.findByUsername(userDetails.getUsername());
        userListService.create(userListDTO, creator);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateList(@PathVariable Long id, @Valid @RequestBody UserListDTO userListDTO) {
        userListService.update(id, userListDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/addTitle")
    public ResponseEntity<?> addTitle(@RequestBody AddTitleToListRequest request) {
        userListService.addTitle(request.getListId(), request.getTitleId());
        return ResponseEntity.ok().build();
    }

    @PutMapping("/removeTitle")
    public ResponseEntity<?> removeTitle(@RequestBody AddTitleToListRequest request) {
        userListService.removeTitle(request.getListId(), request.getTitleId());
        return ResponseEntity.ok().build();
    }

    @PutMapping("/hide/{id}")
    public ResponseEntity<?> hideList(@PathVariable Long id) {
        userListService.hide(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/show/{id}")
    public ResponseEntity<?> showList(@PathVariable Long id) {
        userListService.show(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteList(@PathVariable Long id) {
        userListService.delete(id);
        return ResponseEntity.ok().build();
    }
}
