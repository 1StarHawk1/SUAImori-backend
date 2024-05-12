package com.suaimori.backend.model.dto;

import com.suaimori.backend.model.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserListDTO {
    private Long id;
    private String name;
    private Boolean isVisible;
    private String username;
    //private UserForListDTO user;

    public UserListDTO(Long id, String name, Boolean isVisible) {
        this.id = id;
        this.name = name;
        this.isVisible = isVisible;
    }
}
