package com.suaimori.backend.services;

import com.suaimori.backend.model.entities.Role;
import com.suaimori.backend.model.entities.RoleType;
import com.suaimori.backend.repository.RoleRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;


    @PostConstruct
    public void init() {
        List<Role> roles = new ArrayList<>();
        roles.add(new Role(RoleType.ROLE_ADMIN));
        roles.add(new Role(RoleType.ROLE_RCF));
        roles.add(new Role(RoleType.ROLE_AUTHORISED));

        for (Role role : roles) {
            if (roleRepository.findByName(role.getName()).isEmpty()) {
                roleRepository.save(role);
            }
        }
    }
}
