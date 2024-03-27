package com.suaimori.backend.controllers;

import com.suaimori.backend.model.entities.Franchise;
import com.suaimori.backend.services.FranchiseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.suaimori.backend.model.entities.dto.FranchiseDTO;

@RestController
@RequestMapping("/franchise")
@RequiredArgsConstructor
public class FranchiseController {

    private final FranchiseService franchiseService;

    @PostMapping("/create")
    public ResponseEntity<?> createFranchise(@Valid @RequestBody FranchiseDTO franchiseDTO) {
        Franchise franchise = new Franchise();
        franchise.setName(franchiseDTO.getName());
        franchiseService.create(franchise);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteFranchise(@PathVariable Long id) {
        franchiseService.delete(id);
        return ResponseEntity.ok().build();
    }
}
