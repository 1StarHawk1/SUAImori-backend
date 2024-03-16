package com.suaimori.backend.repository;

import com.suaimori.backend.model.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findByNickname(String nickname);
    Optional<Author> findByFirstNameAndSecondName(String firstName, String secondName);
}
