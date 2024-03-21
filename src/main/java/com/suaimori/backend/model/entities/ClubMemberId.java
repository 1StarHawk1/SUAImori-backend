package com.suaimori.backend.model.entities;

import lombok.Data;

import java.io.Serializable;

@Data
public class ClubMemberId implements Serializable {
    private Long user;
    private Long club;
}
