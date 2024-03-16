package com.suaimori.backend.controllers;

import com.suaimori.backend.model.dto.AuthorDTO;
import com.suaimori.backend.model.entities.Author;
import com.suaimori.backend.services.AuthorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;

@RestController
@RequestMapping("/author")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @PostMapping("/create")
    public ResponseEntity<?> createAuthor(@Valid @RequestBody AuthorDTO authorDTO) {
        Author author = new Author(authorDTO);
        authorService.create(author);
        return ResponseEntity.ok().build();
    }
}
