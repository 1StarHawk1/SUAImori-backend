package com.suaimori.backend.controllers;

import com.suaimori.backend.model.dto.MediaCompanyDTO;
import com.suaimori.backend.model.entities.MediaCompany;
import com.suaimori.backend.services.MediaCompanyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/media-company")
@RequiredArgsConstructor
public class MediaCompanyController {

    private final MediaCompanyService mediaCompanyService;

    @PostMapping("/create")
    public ResponseEntity<?> createMediaCompany(@Valid @RequestBody MediaCompanyDTO mediaCompanyDTO) {
        MediaCompany mediaCompany = new MediaCompany(mediaCompanyDTO);
        mediaCompanyService.create(mediaCompany);
        return ResponseEntity.ok().build();
    }
}
