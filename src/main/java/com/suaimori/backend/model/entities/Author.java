package com.suaimori.backend.model.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "authors")
@Data
public class Author  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nickname;

    @Column
    private String firstName;

    @Column
    private String secondName;

    @Column
    private Date birthDate;

    @Column
    private Date deathDate;

    @Column
    private String info;

    @Column
    private String imageURL;

    @ManyToMany(mappedBy = "authors", fetch = FetchType.LAZY)
    private List<Title> titles = new ArrayList<>();
}
