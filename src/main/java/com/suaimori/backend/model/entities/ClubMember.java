package com.suaimori.backend.model.entities;

import com.suaimori.backend.model.dto.ClubDTO;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "club_user")
@IdClass(ClubMemberId.class)
@Data
public class ClubMember {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "club_id")
    private Club club;

    @Column
    private Boolean isAdmin;

    public ClubDTO getClubDTO() {
        return new ClubDTO(
                club.getId(),
                club.getName(),
                club.getDescription(),
                club.getImageURL(),
                club.getIsVisible()
        );
    }
}
