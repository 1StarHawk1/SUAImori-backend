package com.suaimori.backend.services;

import com.suaimori.backend.model.dto.RatingDTO;
import com.suaimori.backend.model.entities.SerializableRatingId;
import com.suaimori.backend.repository.RatingRepository;
import com.suaimori.backend.model.entities.Title;
import com.suaimori.backend.model.entities.User;
import com.suaimori.backend.model.entities.Rating;
import com.suaimori.backend.repository.TitleRepository;
import com.suaimori.backend.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RatingService {

    private final RatingRepository ratingRepository;
    private final UserRepository userRepository;
    private final TitleRepository titleRepository;

    public void setRating(Rating rating, Title title, User user){
        if(ratingRepository.findByUserAndTitle(user, title).isPresent()){
            throw new RuntimeException("You already rated this title");
        }
        rating.setUser(user);
        rating.setTitle(title);
        ratingRepository.save(rating);
    }

    public void delete(Long userId, Long titleId){
        SerializableRatingId id = new SerializableRatingId(userId, titleId);
        Rating rating = ratingRepository.findById(id).orElseThrow(() -> new RuntimeException("Rating not found"));
        ratingRepository.delete(rating);
    }

    public void hide(Long userId, Long titleId) {
        SerializableRatingId id = new SerializableRatingId(userId, titleId);
        Rating rating = ratingRepository.findById(id).orElseThrow(() -> new RuntimeException("Rating not found"));
        rating.setIsVisible(false);
        ratingRepository.save(rating);
    }

    public void show(Long userId, Long titleId) {
        SerializableRatingId id = new SerializableRatingId(userId, titleId);
        Rating rating = ratingRepository.findById(id).orElseThrow(() -> new RuntimeException("Rating not found"));
        rating.setIsVisible(true);
        ratingRepository.save(rating);
    }
}
