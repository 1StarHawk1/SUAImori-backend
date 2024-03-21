package com.suaimori.backend.model.entities;

import com.suaimori.backend.model.dto.ReviewDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Table(name = "reviews")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Review {

@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String content; //текст отзыва

    @Column
    private Date publicationDate; //дата публикации

    @Column
    private Boolean isVisible; //видимость отзыва

    @Column
    private Date lastEditDate; //дата последнего редактирования

    @Column
    private Boolean isRecommended; //рекомендация отзыва

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "title_id")
    private Title title;

    public Review(ReviewDTO reviewDTO) {
        this.content = reviewDTO.getContent();
        this.isRecommended = reviewDTO.getIsRecommended();
        this.publicationDate = java.sql.Date.valueOf(java.time.LocalDate.now());
        this.lastEditDate = java.sql.Date.valueOf(java.time.LocalDate.now());
    }
}
