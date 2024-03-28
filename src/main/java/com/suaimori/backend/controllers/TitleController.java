package com.suaimori.backend.controllers;

import com.suaimori.backend.model.dto.TitleDTO;
import com.suaimori.backend.services.TitleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/title")
@RequiredArgsConstructor
public class TitleController {

    private final TitleService titleService;

    @PostMapping("/create")
    public ResponseEntity<?> createTitle(@RequestBody TitleDTO titleDTO) throws ChangeSetPersister.NotFoundException {
        titleService.create(titleDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTitle(@PathVariable Long id) {
        titleService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateTitle(@PathVariable Long id, @RequestBody TitleDTO titleDTO) throws ChangeSetPersister.NotFoundException {
        titleService.update(id, titleDTO);
        return ResponseEntity.ok().build();
    }
}
