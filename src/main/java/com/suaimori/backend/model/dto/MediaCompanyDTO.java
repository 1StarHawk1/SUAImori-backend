package com.suaimori.backend.model.dto;

import com.suaimori.backend.model.entities.MediaCompanyType;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MediaCompanyDTO {
    @NotBlank(message = "Name is mandatory")
    private String name;
    private String description;
    private MediaCompanyType type;
}
