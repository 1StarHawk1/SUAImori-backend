package com.suaimori.backend.controllers;

import com.suaimori.backend.model.dto.AddTitleToListRequest;
import com.suaimori.backend.model.dto.UserListDTO;
import com.suaimori.backend.model.entities.User;
import com.suaimori.backend.model.entities.UserList;
import com.suaimori.backend.services.UserListService;
import com.suaimori.backend.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/list")
@RequiredArgsConstructor
public class UserListController {

    private final UserListService userListService;
    private final UserService userService;

    @GetMapping("/getuserlists/{username}")
    public ResponseEntity<?> getUserLists(@PathVariable String username) {
        var userLists = userListService.getUserLists(username);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(userLists);
    }

    @GetMapping("/gettitles/{id}")
    public ResponseEntity<?> getTitles(@PathVariable Long id) {
        List<?> titles = userListService.getTitles(id);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(titles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getList(@PathVariable Long id) {
        UserListDTO userList = userListService.getList(id);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(userList);
    }

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
        try {
            userListService.addTitle(request.getListId(), request.getTitleId());
            return ResponseEntity.ok().build();
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Title already exists in the list");
        }
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
