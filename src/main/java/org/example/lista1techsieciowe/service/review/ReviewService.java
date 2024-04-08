package org.example.lista1techsieciowe.service.review;

import org.example.lista1techsieciowe.entity.Review;
import org.example.lista1techsieciowe.repository.ReviewRepository;
import org.example.lista1techsieciowe.service.book.exceptions.BookDoesntExistException;
import org.example.lista1techsieciowe.service.review.exceptions.ReviewDoesntExistException;
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

    public Review getReview(Integer id) {
        return reviewRepository.findById(id).orElseThrow(() -> ReviewDoesntExistException.create(id));
    }
    public Review addReview(Review review) {
        return reviewRepository.save(review);
    }

    public void deleteReview(Integer id) {
        if (!reviewRepository.existsById(id)) {
            throw BookDoesntExistException.create(id);
        }
        reviewRepository.deleteById(id);
    }

}
