package com.suaimori.backend.services;

import com.suaimori.backend.model.dto.RatingDTO;
import com.suaimori.backend.repository.RatingRepository;
import com.suaimori.backend.model.entities.Title;
import com.suaimori.backend.model.entities.User;
import com.suaimori.backend.model.entities.Rating;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RatingService {

    private final RatingRepository ratingRepository;

    public void setRating(Rating rating, Title title, User user){
        rating.setUser(user);
        rating.setTitle(title);
        ratingRepository.save(rating);
    }
}
