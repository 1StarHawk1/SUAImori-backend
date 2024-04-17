package com.suaimori.backend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String nickname;
    private String email;
    private String description;
    private String avatarURL;
    private String profileWallpaperURL;
}
