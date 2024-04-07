package com.suaimori.backend.controllers;

import com.suaimori.backend.model.dto.MediaCompanyDTO;
import com.suaimori.backend.model.entities.MediaCompany;
import com.suaimori.backend.services.MediaCompanyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/media-company")
@RequiredArgsConstructor
public class MediaCompanyController {

    private final MediaCompanyService mediaCompanyService;

    @PostMapping("/create")
    public ResponseEntity<?> createMediaCompany(@Valid @RequestBody MediaCompanyDTO mediaCompanyDTO) {
        mediaCompanyService.create(mediaCompanyDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteMediaCompany(@PathVariable Long id) {
        mediaCompanyService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateMediaCompany(@PathVariable Long id, @Valid @RequestBody MediaCompanyDTO mediaCompanyDTO) {
        mediaCompanyService.update(id, mediaCompanyDTO);
        return ResponseEntity.ok().build();
    }
}
