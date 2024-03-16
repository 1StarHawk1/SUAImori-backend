package com.suaimori.backend.model.dto;

import com.suaimori.backend.model.entities.MediaCompany;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MediaCompanyForTitleDTO {
    @NotBlank(message = "Name is mandatory")
    private String name;

    public MediaCompany convertToEntity() {
        MediaCompany mediaCompany = new MediaCompany();
        mediaCompany.setName(this.name);
        return mediaCompany;
    }
}
