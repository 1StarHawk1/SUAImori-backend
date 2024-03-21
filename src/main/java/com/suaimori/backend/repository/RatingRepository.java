package com.suaimori.backend.repository;

import com.suaimori.backend.model.entities.Rating;
import com.suaimori.backend.model.entities.Title;
import com.suaimori.backend.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
    Optional<Rating> findByUserAndTitle(User user, Title title);
    void deleteByUserAndTitle(User user, Title title);
}
