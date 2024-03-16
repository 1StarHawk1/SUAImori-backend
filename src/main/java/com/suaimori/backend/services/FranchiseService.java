package com.suaimori.backend.services;

import com.suaimori.backend.model.entities.Franchise;
import com.suaimori.backend.repository.FranchiseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FranchiseService {

    private final FranchiseRepository franchiseRepository;

    public Franchise create(Franchise franchise){
        if(franchiseRepository.findByName(franchise.getName()).isPresent()){
            throw new RuntimeException("Franchise already exists");
        }
        return saveFranchise(franchise);
    }

    private Franchise saveFranchise(Franchise franchise) {
        return franchiseRepository.save(franchise);
    }

    public Franchise findById(Long franchiseId) {
        return franchiseRepository.findById(franchiseId)
                .orElseThrow(() -> new RuntimeException("Franchise not found"));
    }
}
