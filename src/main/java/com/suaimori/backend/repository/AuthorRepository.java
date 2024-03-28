package com.suaimori.backend.repository;

import com.suaimori.backend.model.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findByNickname(String nickname);
    Optional<Author> findByFirstNameAndSecondName(String firstName, String secondName);


    @Modifying
    @Query("DELETE FROM Author a WHERE a.id = :authorId")
    void deleteAuthorWithTitles(@Param("authorId") Long authorId);
}
