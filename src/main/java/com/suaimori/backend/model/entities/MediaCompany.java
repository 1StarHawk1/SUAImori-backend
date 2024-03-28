package com.suaimori.backend.model.entities;

import jakarta.persistence.*;
import lombok.Data;
import com.suaimori.backend.model.dto.MediaCompanyDTO;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "media_companies")
@Data
@NoArgsConstructor
public class MediaCompany {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    @Enumerated(EnumType.STRING)
    private MediaCompanyType type;

    @ManyToMany(mappedBy = "mediaCompanies", fetch = FetchType.LAZY)
    private List<Title> titles = new ArrayList<>();

    public MediaCompany(MediaCompanyDTO mediaCompanyDTO){
        updateFromDto(mediaCompanyDTO);
    }

    public void updateFromDto(MediaCompanyDTO mediaCompanyDTO) {
        this.setName(mediaCompanyDTO.getName());
        this.setDescription(mediaCompanyDTO.getDescription());
        this.setType(mediaCompanyDTO.getType());
    }
}
