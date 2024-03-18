package org.example.lista1techsieciowe.service;

import org.example.lista1techsieciowe.entity.Review;
import org.example.lista1techsieciowe.entity.User;
import org.example.lista1techsieciowe.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {

        this.reviewRepository = reviewRepository;
    }
    public List<Review> getAll() {
        return (List<Review>) reviewRepository.findAll();
    }

    public Review getReview(int id) {
        return reviewRepository.findById(id).orElseThrow(() -> new RuntimeException("Review not found"));
    }
    public Review addReview(Review review) {
        return reviewRepository.save(review);
    }
    public void deleteReview(int id) {
        reviewRepository.deleteById(id);
    }

}
