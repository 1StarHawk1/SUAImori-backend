package com.suaimori.backend.model.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clubs")
@Data
public class Club {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private String imageURL;

    @Column
    private Boolean isVisible;

    @ManyToMany(mappedBy = "clubs", fetch = FetchType.LAZY)
    private List<User> users = new ArrayList<>();
}
