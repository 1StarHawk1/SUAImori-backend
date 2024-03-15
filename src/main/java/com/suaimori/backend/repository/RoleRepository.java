package com.suaimori.backend.repository;

import com.suaimori.backend.model.entities.Role;
import com.suaimori.backend.model.entities.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{
    Role findByName(RoleType name);
}
