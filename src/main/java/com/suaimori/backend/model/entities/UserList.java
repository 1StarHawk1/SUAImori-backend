package com.suaimori.backend.model.entities;

import com.suaimori.backend.model.dto.UserListDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "lists")
@Data
@NoArgsConstructor
public class UserList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private Boolean isVisible;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "list_title",
            joinColumns = @JoinColumn(name = "list_id"),
            inverseJoinColumns = @JoinColumn(name = "title_id")
    )
    private List<Title> titles = new ArrayList<>();

    public UserList(UserListDTO userListDTO) {
        this.name = userListDTO.getName();
        this.isVisible = userListDTO.getIsVisible();

    }

    public void addTitle(Title title){
        titles.add(title);
    }

    public void removeTitle(Title title) {
        titles.remove(title);
    }
}
