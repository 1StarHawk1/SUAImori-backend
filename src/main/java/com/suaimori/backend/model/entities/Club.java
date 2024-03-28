package com.suaimori.backend.model.entities;

import com.suaimori.backend.model.dto.ClubDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clubs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Club {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private String imageURL;

    @Column
    private Boolean isVisible;

//    @ManyToMany(mappedBy = "clubs", fetch = FetchType.LAZY)
//    private List<User> users = new ArrayList<>();

    @OneToMany(mappedBy = "club", fetch = FetchType.LAZY)
    private List<ClubMember> clubMembers = new ArrayList<>();

    public Club(ClubDTO clubDTO){
        updateFromDto(clubDTO);
    }

    public void updateFromDto(ClubDTO clubDTO) {
        this.setName(clubDTO.getName());
        this.setDescription(clubDTO.getDescription());
        this.setImageURL(clubDTO.getImageURL());
        this.setIsVisible(clubDTO.getIsVisible());
    }
}
