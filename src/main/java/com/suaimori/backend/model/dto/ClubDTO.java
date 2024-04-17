package com.suaimori.backend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClubDTO {
    private Long id;
    private String name;
    private String description;
    private String imageURL;
    private Boolean isVisible;
}
