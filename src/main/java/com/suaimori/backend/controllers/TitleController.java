package com.suaimori.backend.controllers;

import com.suaimori.backend.model.dto.TitleDTO;
import com.suaimori.backend.services.TitleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/title")
@RequiredArgsConstructor
public class TitleController {

    private final TitleService titleService;

    @PostMapping("/create")
    public ResponseEntity<?> createTitle(@RequestBody TitleDTO titleDTO) throws ChangeSetPersister.NotFoundException {
        //Title title = new Title(titleDTO);
        titleService.create(titleDTO);
        return ResponseEntity.ok().build();
    }
}