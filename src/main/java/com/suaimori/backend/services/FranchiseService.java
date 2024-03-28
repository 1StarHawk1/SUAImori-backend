package com.suaimori.backend.services;

import com.suaimori.backend.model.entities.Franchise;
import com.suaimori.backend.repository.FranchiseRepository;
import com.suaimori.backend.model.entities.dto.FranchiseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class FranchiseService {

    private final FranchiseRepository franchiseRepository;

    public void create(FranchiseDTO franchiseDTO){
        if(franchiseRepository.findByName(franchiseDTO.getName()).isPresent()){
            throw new RuntimeException("Franchise already exists");
        }
        Franchise franchise = new Franchise(franchiseDTO);
        franchiseRepository.save(franchise);
    }

    public Franchise findById(Long franchiseId) {
        return franchiseRepository.findById(franchiseId)
                .orElseThrow(() -> new RuntimeException("Franchise not found"));
    }

    public void delete(Long id){
        franchiseRepository.deleteById(id);
    }

    public void update(Long id, com.suaimori.backend.model.entities.dto.FranchiseDTO franchiseDTO) {
        Franchise franchise = findById(id);
        franchise.updateFromDto(franchiseDTO);
        franchiseRepository.save(franchise);
    }
}
