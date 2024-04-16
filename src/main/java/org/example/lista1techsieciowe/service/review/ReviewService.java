package org.example.lista1techsieciowe.service.review;

import org.example.lista1techsieciowe.commonTypes.UserRole;
import org.example.lista1techsieciowe.controller.dto.book.GetBookDto;
import org.example.lista1techsieciowe.controller.dto.review.ReviewDto;
import org.example.lista1techsieciowe.controller.dto.review.ReviewResponseDto;
import org.example.lista1techsieciowe.controller.dto.user.GetUserDto;
import org.example.lista1techsieciowe.entity.Book;
import org.example.lista1techsieciowe.entity.Review;
import org.example.lista1techsieciowe.entity.User;
import org.example.lista1techsieciowe.repository.BookRepository;
import org.example.lista1techsieciowe.repository.LoginRepository;
import org.example.lista1techsieciowe.repository.ReviewRepository;
import org.example.lista1techsieciowe.repository.UserRepository;
import org.example.lista1techsieciowe.service.auth.OwnershipService;
import org.example.lista1techsieciowe.service.auth.exceptions.UnauthorizedException;
import org.example.lista1techsieciowe.service.auth.exceptions.UserNotFoundException;
import org.example.lista1techsieciowe.service.auth.exceptions.UserWithGivenLoginDoesntExistException;
import org.example.lista1techsieciowe.service.book.exceptions.BookDoesntExistException;
import org.example.lista1techsieciowe.service.review.exceptions.ReviewDoesntExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService extends OwnershipService {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final LoginRepository loginRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository, UserRepository userRepository, BookRepository bookRepository, LoginRepository loginRepository) {
        super(loginRepository);
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.loginRepository = loginRepository;
    }
    public List<ReviewResponseDto> getAll() {
        List<Review> review = (List<Review>) reviewRepository.findAll();
        return review.stream().map(this::mapReview).collect(Collectors.toList());

    }

    public ReviewResponseDto getReview(Integer id) {
        Review review = reviewRepository.findById(id)
            .orElseThrow(() -> ReviewDoesntExistException.create(id));
        return mapReview(review);
    }
    private ReviewResponseDto mapReview(Review review) {
        GetUserDto user = new GetUserDto(
                review.getUser().getId(),
                review.getUser().getName(),
                review.getUser().getEmail());
        GetBookDto book = new GetBookDto(
                review.getBook().getBookId(),
                review.getBook().getIsbn(),
                review.getBook().getPublisher(),
                review.getBook().getAuthor(),
                review.getBook().getTitle(),
                review.getBook().getYearOfPublish(),
                review.getBook().getAvailableCopies()>0);
        return new ReviewResponseDto(review.getReviewId(), book, user, review.getGrade(), review.getComment(), review.getReviewDate());
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

        GetBookDto getBookDto = new GetBookDto(
                book.getBookId(),
                book.getIsbn(),
                book.getPublisher(),
                book.getAuthor(),
                book.getTitle(),
                book.getYearOfPublish(),
                book.getAvailableCopies() > 0);

        GetUserDto getUserDto = new GetUserDto(
                user.getId(),
                user.getName(),
                user.getEmail());

        return new ReviewResponseDto(newReview.getReviewId(), getBookDto, getUserDto, newReview.getGrade(), newReview.getComment(), newReview.getReviewDate());
    }
    @PreAuthorize("hasRole('LIBRARIAN') or isAuthenticated() and @ownershipService.isOwner(authentication.name, #userId)")
    public void deleteReview(Integer id, Integer userId) {
        if (!reviewRepository.existsById(id)) {
            throw ReviewDoesntExistException.create(id);
        }

        Review review = reviewRepository.findById(id).orElseThrow(() -> ReviewDoesntExistException.create(id));

        if (isOwner(review.getUser().getLogin().getUsername(), userId) || review.getUser().getLogin().getRole() == UserRole.ROLE_LIBRARIAN) {
            reviewRepository.deleteById(id);
        } else {
            throw UnauthorizedException.create();
        }
    }

    @PreAuthorize("isAuthenticated() and @ownershipService.isOwner(authentication.name, #userId)")
    public ReviewResponseDto updateReview(Integer id, ReviewDto updatedReview, Integer userId) {
        Review existingReview = reviewRepository.findById(id).orElseThrow(() -> ReviewDoesntExistException.create(id));
        Book book = bookRepository.findById(updatedReview.getBook())
                .orElseThrow(() -> BookDoesntExistException.create(updatedReview.getBook()));
        User user = userRepository.findById(updatedReview.getUser())
                .orElseThrow(() -> UserNotFoundException.create(updatedReview.getUser()));

        if (!isOwner(existingReview.getUser().getLogin().getUsername(), userId)) {
            throw UnauthorizedException.create();
        }

        existingReview.setBook(book);
        existingReview.setUser(user);
        existingReview.setGrade(updatedReview.getGrade());
        existingReview.setComment(updatedReview.getComment());
        existingReview.setReviewDate(updatedReview.getReviewDate());

        Review savedReview = reviewRepository.save(existingReview);
        return mapReview(savedReview);
    }
}
