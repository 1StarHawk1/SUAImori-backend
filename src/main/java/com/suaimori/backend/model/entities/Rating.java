package com.suaimori.backend.model.entities;

import com.suaimori.backend.model.dto.RatingDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ratings")
@Data
@IdClass(SerializableRatingId.class)
@NoArgsConstructor
@AllArgsConstructor
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

    public Rating(RatingDTO ratingDTO) {
        this.value = ratingDTO.getValue();
        this.isVisible = true;
    }

}
