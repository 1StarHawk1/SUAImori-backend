package com.suaimori.backend.model.dto;

import com.suaimori.backend.model.entities.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TitleDTO {
    @NotBlank(message = "Name is mandatory")
    private String name;
    private String posterURL;
    private Integer itemCount;
    private TitleStatus status;
    private Date releaseDate;
    private Date complitionDate;
    private String description;
    private TitleType type;
    private Boolean isNSFW;
    private Franchise franchise;
    private List<AuthorForTitleDTO> authors = new ArrayList<>();
    private List<MediaCompanyForTitleDTO> mediaCompanies = new ArrayList<>();
}
