package com.suaimori.backend.services;

import com.suaimori.backend.model.entities.Review;
import com.suaimori.backend.model.entities.Title;
import com.suaimori.backend.model.entities.User;
import com.suaimori.backend.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public void create(Review review, Title title, User creator){
        if(reviewRepository.findByUserAndTitle(creator, title).isPresent()){
            throw new RuntimeException("You already have review for this title");
        }
        review.setUser(creator);
        review.setTitle(title);
        reviewRepository.save(review);
    }

    public void delete(Long id){
        reviewRepository.deleteById(id);
    }

    public void hide(Long id) {
        Review review = reviewRepository.findById(id).orElseThrow(() -> new RuntimeException("Review not found"));
        review.setIsVisible(false);
        reviewRepository.save(review);
    }

    public void show(Long id) {
        Review review = reviewRepository.findById(id).orElseThrow(() -> new RuntimeException("Review not found"));
        review.setIsVisible(true);
        reviewRepository.save(review);
    }
}
