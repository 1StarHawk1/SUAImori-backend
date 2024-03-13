package com.suaimori.backend.model.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "media_companies")
@Data
public class MediaCompany {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private MediaCompanyType type;

    @ManyToMany(mappedBy = "mediaCompanies", fetch = FetchType.LAZY)
    private List<Title> titles = new ArrayList<>();
}
