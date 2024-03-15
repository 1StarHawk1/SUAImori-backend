package com.suaimori.backend.model.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Collection;

@Entity
@Data
@Table(name="users")
@NoArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public static class Builder {
        private String username;
        private String email;
        private String password;
        private List<Role> roles = new ArrayList<>();

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder role(Role role) {
            this.roles.add(role);
            return this;
        }

        public User build() {
            User user = new User();
            user.username = this.username;
            user.email = this.email;
            user.password = this.password;
            user.roles = this.roles;
            return user;
        }
    }
}
