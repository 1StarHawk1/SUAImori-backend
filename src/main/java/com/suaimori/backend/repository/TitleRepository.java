package com.suaimori.backend.repository;
import com.suaimori.backend.model.entities.Title;

import com.suaimori.backend.model.entities.TitleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TitleRepository extends JpaRepository<Title, Long> {
    Optional<Title> findByName(String name);
    List<Title> findByType(TitleType type);
    @Query("SELECT t.id FROM Title t")
    List<Long> findAllIds();

    @Query("SELECT t.id FROM Title t WHERE t.type = 'ANIME'")
    List<Long> findAllAnimeIds();

    @Query("SELECT t.id FROM Title t WHERE t.type = 'MANGA'")
    List<Long> findAllMangaIds();

    @Query("SELECT t.id FROM Title t WHERE t.type = 'ANIME' AND t.status = 'ONGOING'")
    List<Long> findOngoingAnimeIds();
}
