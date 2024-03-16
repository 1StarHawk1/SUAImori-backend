package com.suaimori.backend.repository;
import com.suaimori.backend.model.entities.Title;

import com.suaimori.backend.model.entities.TitleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TitleRepository extends JpaRepository<Title, Long> {
    Optional<Title> findByName(String name);
    List<Title> findByType(TitleType type);
}
