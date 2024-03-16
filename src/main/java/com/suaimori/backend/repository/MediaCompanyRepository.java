package com.suaimori.backend.repository;

import com.suaimori.backend.model.entities.MediaCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MediaCompanyRepository extends JpaRepository<MediaCompany, Long> {
    Optional<MediaCompany> findByName(String name);
}
