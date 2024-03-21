package com.suaimori.backend.model.entities;

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
}
