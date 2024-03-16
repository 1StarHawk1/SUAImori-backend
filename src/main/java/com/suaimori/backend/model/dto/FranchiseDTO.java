package com.suaimori.backend.model.entities.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FranchiseDTO {
    @NotBlank(message = "Name is mandatory")
    private String name;
}
