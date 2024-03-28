package com.suaimori.backend.model.entities;

import jakarta.persistence.*;
import lombok.Data;
import com.suaimori.backend.model.entities.dto.FranchiseDTO;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "franchaises")
@Data
@NoArgsConstructor
public class Franchise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @OneToMany(mappedBy = "franchise", fetch = FetchType.LAZY)
    private List<Title> titles = new ArrayList<>();

    public Franchise(FranchiseDTO franchiseDTO) {
        updateFromDto(franchiseDTO);
    }
    public void updateFromDto(com.suaimori.backend.model.entities.dto.FranchiseDTO franchiseDTO) {
        this.name = franchiseDTO.getName();
    }
}
