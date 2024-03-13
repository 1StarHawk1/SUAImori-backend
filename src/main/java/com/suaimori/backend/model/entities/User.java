package com.suaimori.backend.model.entities;


import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Collection;

@Entity
@Data
@Table(name="users")
public class User implements UserDetails {

    @Id
    private Long id;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles = new ArrayList<>();

    @Column
    private String username; //Логин

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private String nickname;

    @Column
    private Date birthDate;

    @Column
    private String avatarURL;

    @Column
    private String profileWallpaperURL;

    @Column
    private String description;

    @Column
    private Sex sex;

    @Column
    private Boolean isAllowedNSFW;

    @Column
    private Date registrationDate;

    @Column
    private Boolean isBanned;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "club_user",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "club_id"))
    private List<Club> clubs = new ArrayList<>();

    @OneToMany(mappedBy = "requestSender", fetch = FetchType.LAZY)
    private List<Friend> sentRequests = new ArrayList<>();

    @OneToMany(mappedBy = "requestReceiver", fetch = FetchType.LAZY)
    private List<Friend> receivedRequests = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<UserList> lists = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Comment> comments = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

}
