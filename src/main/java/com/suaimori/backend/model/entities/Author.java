package com.suaimori.backend.model.entities;

import com.suaimori.backend.model.dto.AuthorDTO;
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

    @ManyToMany(cascade = CascadeType.PERSIST, mappedBy = "authors", fetch = FetchType.LAZY)
    private List<Title> titles = new ArrayList<>();

    public Author(AuthorDTO authorDTO){
        this.setFirstName(authorDTO.getFirstName());
        this.setSecondName(authorDTO.getSecondName());
        this.setNickname(authorDTO.getNickname());
        this.setBirthDate(authorDTO.getBirthDate());
        this.setDeathDate(authorDTO.getDeathDate());
        this.setInfo(authorDTO.getInfo());
        this.setImageURL(authorDTO.getImageURL());
    }

    public Author() {
    }
}
