package com.suaimori.backend.controllers;

import com.suaimori.backend.model.dto.AddTitleToListRequest;
import com.suaimori.backend.model.dto.UserListDTO;
import com.suaimori.backend.model.entities.UserList;
import com.suaimori.backend.services.UserListService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/list")
@RequiredArgsConstructor
public class UserListController {

    private final UserListService userListService;

    @PostMapping("/create")
    public ResponseEntity<?> createList(@RequestBody UserListDTO userListDTO) throws ChangeSetPersister.NotFoundException {
        //UserList userList = new UserList(userListDTO);
        userListService.create(userListDTO);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/addTitle")
    public ResponseEntity<?> addTitle(@RequestBody AddTitleToListRequest request) {
        userListService.addTitle(request.getName(), request.getTitle());
        return ResponseEntity.ok().build();
    }

}
