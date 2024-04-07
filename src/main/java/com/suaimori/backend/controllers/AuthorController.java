package com.suaimori.backend.controllers;

import com.suaimori.backend.model.dto.AuthorDTO;
import com.suaimori.backend.model.entities.Author;
import com.suaimori.backend.services.AuthorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/author")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @PostMapping("/create")
    public ResponseEntity<?> createAuthor(@Valid @RequestBody AuthorDTO authorDTO) {
        authorService.create(authorDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAuthor(@PathVariable Long id) {
        authorService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateAuthor(@PathVariable Long id, @Valid @RequestBody AuthorDTO authorDTO) {
        authorService.update(id, authorDTO);
        return ResponseEntity.ok().build();
    }
}
