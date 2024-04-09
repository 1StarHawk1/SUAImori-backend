package com.suaimori.backend.repository;

import com.suaimori.backend.model.entities.Club;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClubRepository extends JpaRepository<Club, Long>{
    Optional<Club> findByName(String name);

    @Query("SELECT c.id FROM Club c")
    List<Long> findAllIds();
}
