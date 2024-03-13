package com.suaimori.backend.model.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name="titles")
public class Title {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String posterURL;

    @Column
    private Integer itemCount; //Количество глав или серий

    @Column
    private TitleStatus status; //Выходит, вышло или анонсированно

    @Column
    private Date releaseDate;

    @Column
    private Date complitionDate;

    @Column
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "franchise_id")
    private Franchise franchise;

    @Column
    private TitleType type;

    @Column
    private Boolean isNSFW;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "author_title",
            joinColumns = @JoinColumn(name = "title_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    private List<Author> authors = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "title_mediacompany",
            joinColumns = @JoinColumn(name = "title_id"),
            inverseJoinColumns = @JoinColumn(name = "media_company_id"))
    private List<MediaCompany> mediaCompanies = new ArrayList<>();

    @OneToMany(mappedBy = "title", fetch = FetchType.LAZY)
    private List<News> news = new ArrayList<>();

    @ManyToMany(mappedBy = "titles", fetch = FetchType.LAZY)
    private List<UserList> userLists = new ArrayList<>();

    @OneToMany(mappedBy = "title", fetch = FetchType.LAZY)
    private List<Comment> comments = new ArrayList<>();
}
