package com.suaimori.backend.model.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "ratings")
@Data
@IdClass(SerializableRatingId.class)
public class Rating {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "title_id")
    private Title title;

    @Column
    private Integer value;

    @Column
    private Boolean isVisible;

}
