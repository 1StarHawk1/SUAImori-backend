package com.suaimori.backend.model.entities;


import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Collection;
import java.util.Collections;

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


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
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
