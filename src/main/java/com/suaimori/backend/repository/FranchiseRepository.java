package com.suaimori.backend.repository;

import com.suaimori.backend.model.entities.Franchise;
import com.suaimori.backend.model.entities.Role;
import com.suaimori.backend.model.entities.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FranchiseRepository extends JpaRepository<Franchise, Long> {
    Optional<Franchise> findByName(String name);
    Optional<Franchise> findById(Long id);
}
