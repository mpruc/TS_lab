package org.example.lista1techsieciowe.service.review;

import org.example.lista1techsieciowe.controller.dto.BookDetailsDto;
import org.example.lista1techsieciowe.controller.dto.BookDetailsResponseDto;
import org.example.lista1techsieciowe.controller.dto.ReviewDto;
import org.example.lista1techsieciowe.controller.dto.ReviewResponseDto;
import org.example.lista1techsieciowe.entity.Book;
import org.example.lista1techsieciowe.entity.BookDetails;
import org.example.lista1techsieciowe.entity.Review;
import org.example.lista1techsieciowe.entity.User;
import org.example.lista1techsieciowe.repository.BookRepository;
import org.example.lista1techsieciowe.repository.ReviewRepository;
import org.example.lista1techsieciowe.repository.UserRepository;
import org.example.lista1techsieciowe.service.auth.exceptions.UserWithGivenLoginDoesntExistException;
import org.example.lista1techsieciowe.service.book.exceptions.BookDoesntExistException;
import org.example.lista1techsieciowe.service.review.exceptions.ReviewDoesntExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    @Autowired
    public ReviewService(ReviewRepository reviewRepository, UserRepository userRepository, BookRepository bookRepository) {

        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }
    public List<Review> getAll() {
        return (List<Review>) reviewRepository.findAll();
    }

    public Review getReview(Integer id) {
        return reviewRepository.findById(id).orElseThrow(() -> ReviewDoesntExistException.create(id));
    }
    public ReviewResponseDto addReview(ReviewDto review) {
        User user = userRepository.findById(review.getUser())
                .orElseThrow(() -> UserWithGivenLoginDoesntExistException.create(String.valueOf(review.getUser())));

        Book book = bookRepository.findById(review.getBook())
                .orElseThrow(() -> BookDoesntExistException.create(review.getBook()));

        var reviewEntity = new Review();
        reviewEntity.setReviewDate(review.getReviewDate());
        reviewEntity.setUser(user);
        reviewEntity.setBook(book);
        reviewEntity.setComment(review.getComment());
        reviewEntity.setGrade(review.getGrade());

        var newReview = reviewRepository.save(reviewEntity);
        return new ReviewResponseDto(newReview.getReviewId(),
                newReview.getBook(),
                newReview.getUser(),
                newReview.getGrade(),
                newReview.getComment(),
                newReview.getReviewDate()
                );
    }


    public void deleteReview(Integer id) {
        if (!reviewRepository.existsById(id)) {
            throw BookDoesntExistException.create(id);
        }
        reviewRepository.deleteById(id);
    }

}
