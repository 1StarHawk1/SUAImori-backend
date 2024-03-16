package com.suaimori.backend.model.dto;

import com.suaimori.backend.model.entities.Author;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorForTitleDTO {
    private String nickname;
    @NotBlank(message = "First name is mandatory")
    private String firstName;
    @NotBlank(message = "Second name is mandatory")
    private String secondName;
    private String imageURL;

    public Author convertToEntity() {
        Author author = new Author();
        author.setNickname(this.nickname);
        author.setFirstName(this.firstName);
        author.setSecondName(this.secondName);
        author.setImageURL(this.imageURL);
        return author;
    }

}
