package com.suaimori.backend.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDTO {
    private String nickname;
    @NotBlank(message = "First name is mandatory")
    private String firstName;
    @NotBlank(message = "Second name is mandatory")
    private String secondName;
    private Date birthDate;
    private Date deathDate;
    private String info;
    private String imageURL;
}
