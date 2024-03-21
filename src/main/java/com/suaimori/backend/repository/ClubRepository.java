package com.suaimori.backend.repository;

import com.suaimori.backend.model.entities.Club;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Repository
public interface ClubRepository extends JpaRepository<Club, Long>{
    Optional<Club> findByName(String name);
}
