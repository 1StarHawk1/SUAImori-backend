package com.suaimori.backend.model.dto;

import com.suaimori.backend.model.entities.MediaCompany;
import com.suaimori.backend.model.entities.User;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserForListDTO {
    @NotBlank(message = "Username is mandatory")
    private String username;
}
