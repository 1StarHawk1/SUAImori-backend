package com.suaimori.backend.model.entities;

import com.suaimori.backend.model.dto.CommentDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;

@Entity
@Table(name = "comments")
@Data
@NoArgsConstructor
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

    public Comment(CommentDTO commentDTO){
        this.content = commentDTO.getContent();
        this.publicationDate = java.sql.Date.valueOf(LocalDate.now());
        this.lastEditDate = java.sql.Date.valueOf(LocalDate.now());
    }
}
