package com.suaimori.backend.model.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Data
@Table(name="roles")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private RoleType name;

    public Role() {
    }

    private String getName(){
        return this.name.toString();
    }
    @Override
    public String getAuthority() {
        return getName();
    }

    public Role(Long id) {
        this.id = id;
    }

    public Role(Long id, RoleType name) {
        this.id = id;
        this.name = name;
    }
}
