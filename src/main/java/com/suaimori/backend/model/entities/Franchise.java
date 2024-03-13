package com.suaimori.backend.model.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "franchaises")
@Data
public class Franchise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @OneToMany(mappedBy = "franchise", fetch = FetchType.LAZY)
    private List<Title> titles = new ArrayList<>();
}
