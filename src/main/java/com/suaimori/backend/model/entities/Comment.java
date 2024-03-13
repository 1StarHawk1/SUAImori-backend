package com.suaimori.backend.model.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Entity
@Table(name = "comments")
@Data
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String content; //текст комментария

    @Column
    private Date publicationDate; //дата публикации

    @Column
    private Boolean isVisible; //видимость комментария

    @Column
    private Date lastEditDate; //дата последнего редактирования

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "title_id")
    private Title title;
}
