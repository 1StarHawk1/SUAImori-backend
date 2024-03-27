package com.suaimori.backend.model.dto;

import com.suaimori.backend.model.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserListDTO {
    private String name;
    private Boolean isVisible;
    //private UserForListDTO user;
}
