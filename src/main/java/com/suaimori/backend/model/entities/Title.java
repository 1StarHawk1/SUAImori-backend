package com.suaimori.backend.model.entities;

import com.suaimori.backend.model.dto.AuthorForTitleDTO;
import com.suaimori.backend.model.dto.MediaCompanyForTitleDTO;
import com.suaimori.backend.model.dto.TitleDTO;
import com.suaimori.backend.services.AuthorService;
import com.suaimori.backend.services.MediaCompanyService;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Data
@Table(name="titles")
public class Title {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String posterURL;

    @Column
    private Integer itemCount; //Количество глав или серий

    @Column
    @Enumerated(EnumType.STRING)
    private TitleStatus status; //Выходит, вышло или анонсированно

    @Column
    private Date releaseDate;

    @Column
    private Date complitionDate;

    @Column
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "franchise_id")
    private Franchise franchise;

    @Column
    @Enumerated(EnumType.STRING)
    private TitleType type;

    @Column
    private Boolean isNSFW;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "author_title",
            joinColumns = @JoinColumn(name = "title_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    private List<Author> authors = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "title_mediacompany",
            joinColumns = @JoinColumn(name = "title_id"),
            inverseJoinColumns = @JoinColumn(name = "media_company_id"))
    private List<MediaCompany> mediaCompanies = new ArrayList<>();

    @OneToMany(mappedBy = "title", fetch = FetchType.LAZY)
    private List<News> news = new ArrayList<>();

    @ManyToMany(mappedBy = "titles", fetch = FetchType.LAZY)
    private List<UserList> userLists = new ArrayList<>();

    @OneToMany(mappedBy = "title", fetch = FetchType.LAZY)
    private List<Comment> comments = new ArrayList<>();


    public Title(TitleDTO titleDTO){
        this.setName(titleDTO.getName());
        this.setPosterURL(titleDTO.getPosterURL());
        this.setItemCount(titleDTO.getItemCount());
        this.setStatus(titleDTO.getStatus());
        this.setReleaseDate(titleDTO.getReleaseDate());
        this.setComplitionDate(titleDTO.getComplitionDate());
        this.setDescription(titleDTO.getDescription());
        //this.setFranchise(titleDTO.getFranchise());
        this.setType(titleDTO.getType());
        this.setIsNSFW(titleDTO.getIsNSFW());

        //this.setAuthors(convertToAuthorEntityList(titleDTO.getAuthors()));

        //this.setMediaCompanies(convertToMediaCompanyEntityList(titleDTO.getMediaCompanies()));
    }

    public List<Author> convertToAuthorEntityList(List<AuthorForTitleDTO> authorForTitleDTOList) {
        return authorForTitleDTOList.stream()
                .map(AuthorForTitleDTO::convertToEntity)
                .collect(Collectors.toList());
    }

    public List<MediaCompany> convertToMediaCompanyEntityList(List<MediaCompanyForTitleDTO> mediaCompanyForTitleDTOList) {
        return mediaCompanyForTitleDTOList.stream()
                .map(MediaCompanyForTitleDTO::convertToEntity)
                .collect(Collectors.toList());
    }

    public Title() {
    }
}
