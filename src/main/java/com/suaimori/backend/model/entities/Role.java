package com.suaimori.backend.model.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Data
@Table(name="roles")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Column
    @Enumerated(EnumType.STRING)
    private RoleType name;

    public Role() {
    }

    public Role(RoleType roleType) {
        this.name = roleType;
    }


    @Override
    public String getAuthority() {
        return getName().toString();
    }

    public Role(Long id) {
        this.id = id;
    }

    public Role(Long id, RoleType name) {
        this.id = id;
        this.name = name;
    }

}
